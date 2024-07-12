package example.hook.project.hook;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Composite hook that combines multiple hooks into one.
 * It executes the hooks in sequence for both before and after processing.
 * 
 * The CompositeWeatherDataHook class takes a list of WeatherDataHook instances and combines them into a single composite hook.
 */

public class CompositeWeatherDataHook<CONTEXT> implements WeatherDataHook<CONTEXT> {

    // List of hooks to be executed in sequence.
    private final List<WeatherDataHook<CONTEXT>> hooks;

    // Constructor that initializes the CompositeWeatherDataHook with a list of hooks.
    public CompositeWeatherDataHook(List<WeatherDataHook<CONTEXT>> hooks) {
        this.hooks = hooks;
    }

     /**
     * This method is called before the main processing logic.
     * It sequentially executes the `beforeProcessing` method of each hook in the list.
     * 
     * @param context the context containing data to be processed
     * @return a CompletableFuture with the updated context
     */
    @Override
    public CompletableFuture<CONTEXT> beforeProcessing(CONTEXT context){
         // Start with a completed future containing the initial context.
        CompletableFuture<CONTEXT> future = CompletableFuture.completedFuture(context);

        // Sequentially chain the beforeProcessing methods of all hooks.
        for (WeatherDataHook<CONTEXT> hook : hooks) {
            // For each hook, update the future to include its beforeProcessing method.
            future = future.thenCompose(hook::beforeProcessing);
        }
        // Return the final future which represents the sequential execution of all hooks.
        return future;
    }

    /**
     * This method is called after the main processing logic.
     * It sequentially executes the `afterProcessing` method of each hook in the list.
     * 
     * @param context the context containing data to be processed
     * @return a CompletableFuture with the updated context
     */

    @Override
    public CompletableFuture<CONTEXT> afterProcessing(CONTEXT context) {
        // Start with a completed future containing the initial context.
        CompletableFuture<CONTEXT> future = CompletableFuture.completedFuture(context);

        // Sequentially chain the afterProcessing methods of all hooks.
        for (WeatherDataHook<CONTEXT> hook : hooks) {
            // For each hook, update the future to include its afterProcessing method.
            future = future.thenCompose(hook::afterProcessing);
        }
        // Return the final future which represents the sequential execution of all hooks.
        return future;
    }
    
}
