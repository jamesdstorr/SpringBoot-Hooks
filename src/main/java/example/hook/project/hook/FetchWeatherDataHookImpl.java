package example.hook.project.hook;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import example.hook.project.collaborator.client.OpenCageClient;
import example.hook.project.context.WeatherDataContext;
import example.hook.project.hook.annotations.FetchWeatherDataHooks;
import example.hook.project.model.openCage.OpenCageResponse;
import example.hook.project.model.openCage.Result;

import org.springframework.beans.factory.annotation.Value;

@FetchWeatherDataHooks
@Component
public class FetchWeatherDataHookImpl implements WeatherDataHook<WeatherDataContext> {

    private final OpenCageClient openCageClient;
    private final ObjectMapper objectMapper; // JSON object mapper

    @Value("${opencage.api.key}")
    private String openCageApiKey;

    public FetchWeatherDataHookImpl(OpenCageClient openCageClient) {
        this.openCageClient = openCageClient;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public CompletableFuture<WeatherDataContext> beforeProcessing(WeatherDataContext context) {

        return CompletableFuture.supplyAsync(() -> {
            try {
                // Get coordinates from OpenCage API
                String response = openCageClient.getCoordinates(context.getLocation(), openCageApiKey);
                // Parse the response to get latitude and longitude
                OpenCageResponse openCageResponse = objectMapper.readValue(response, OpenCageResponse.class);
                Result result = openCageResponse.getResults().stream().findFirst().orElseThrow(() -> new RuntimeException("No results found"));
                context.setLat(String.valueOf(result.getGeometry().getLatitude()));
                context.setLon(String.valueOf(result.getGeometry().getLongitude()));
            } catch (Exception e) {
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
