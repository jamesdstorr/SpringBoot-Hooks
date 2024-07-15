package example.hook.project.hook;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import example.hook.project.context.WeatherDataContext;
import example.hook.project.hook.annotations.ReturnWeatherDataHooks;
import example.hook.project.model.weatherAPI.dto.Forecast;

@ReturnWeatherDataHooks
@Component
public class ReturnWeatherDataHookImpl implements WeatherDataHook<WeatherDataContext> {
    
    private final ObjectMapper objectMapper;

    public ReturnWeatherDataHookImpl() {
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public CompletableFuture<WeatherDataContext> beforeProcessing(WeatherDataContext context){
        return CompletableFuture.supplyAsync(() -> {
            try {
                JsonNode rootNode = objectMapper.readTree(context.getRawData());
                JsonNode forecastNode = rootNode.path("forecast");
                Forecast forecast = objectMapper.treeToValue(forecastNode, Forecast.class);
                context.setProcessedData(forecast);
            } catch (JsonProcessingException e) {
                context.setError(e);
            }
            return context;
        });
    }

    @Override
    public CompletableFuture<WeatherDataContext> afterProcessing(WeatherDataContext context) {
        // No after processing logic needed for this hook
        return CompletableFuture.completedFuture(context);
    }

}
