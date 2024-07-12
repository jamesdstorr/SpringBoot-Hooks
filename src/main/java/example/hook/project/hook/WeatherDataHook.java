package example.hook.project.hook;

import java.util.concurrent.CompletableFuture;

/**
 * This interface defines the contract for hooks that can be used to process weather data.
 * It uses a generic type parameter CONTEXT, which allows it to be used with different types of contexts.
 */
public interface WeatherDataHook<CONTEXT> {
    /**
     * Method to be executed before the main processing logic.
     * @param context the context containing data to be processed
     * @return a CompletableFuture with the updated context
     */
    CompletableFuture<CONTEXT> beforeProcessing(CONTEXT context);

    /**
     * Method to be executed after the main processing logic.
     * @param context the context containing data to be processed
     * @return a CompletableFuture with the updated context
     */
    CompletableFuture<CONTEXT> afterProcessing(CONTEXT context);
}
