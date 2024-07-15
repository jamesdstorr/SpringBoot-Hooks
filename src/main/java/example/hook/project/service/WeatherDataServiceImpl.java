package example.hook.project.service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import example.hook.project.collaborator.weatherAPI.WeatherAPIClient;
import example.hook.project.context.WeatherDataContext;
import example.hook.project.hook.WeatherDataHook;
import example.hook.project.hook.WeatherDataHookEnum;
import example.hook.project.model.weatherAPI.dto.Forecast;

@Service
public class WeatherDataServiceImpl {
    private final WeatherAPIClient weatherAPIClient; //Feign Client
    private final WeatherDataHook<WeatherDataContext> fetchWeatherDataHook; //Composite Hok for fetching Weather Data
    private final WeatherDataHook<WeatherDataContext> enhanceWeatherDataHook; //Composite Hok for enhancing Weather Data
    private final WeatherDataHook<WeatherDataContext> returnWeatherDataHook; //Composite Hok for persisting Weather Data

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherDataServiceImpl(WeatherAPIClient weatherAPIClient,
            Map<WeatherDataHookEnum, WeatherDataHook<WeatherDataContext>> weatherDataHookMap) {
        this.weatherAPIClient = weatherAPIClient;
        // Retrieve the fetch weather data hook from the map using the enum
        this.fetchWeatherDataHook = weatherDataHookMap.get(WeatherDataHookEnum.FETCH_WEATHER_DATA);
        // Retrieve the enhance weather data hook from the map using the enum
        this.enhanceWeatherDataHook = weatherDataHookMap.get(WeatherDataHookEnum.ENHANCE_WEATHER_DATA);
        this.returnWeatherDataHook = weatherDataHookMap.get(WeatherDataHookEnum.RETURN_WEATHER_DATA);
    }

    public CompletableFuture<Forecast> fetchWeatherForecast(String location) {
        // Create a WeatherDataContext with the given location
        WeatherDataContext weatherDataContext = WeatherDataContext.builder().location(location).build();

        // Execute the beforeProcessing hooks for fetching data
        return fetchWeatherDataHook.beforeProcessing(weatherDataContext)
                .thenCompose(ctx -> {
                    try {
                        // Call the OpenWeather API using the Feign client
                        //String rawData = openWeatherClient.getSixteenDayForecaset(ctx.getLon(), ctx.getLat(), apiKey);
                        String rawData = weatherAPIClient.getWeatherForecast(ctx.getLocation(), "3", apiKey);
                        // Set the raw data in the context
                        ctx.setRawData(rawData);
                    } catch (Exception e) {
                        // Handle any exceptions by setting the error in the context
                        ctx.setError(e);
                    }
                    // Return the updated context
                    return CompletableFuture.completedFuture(ctx);
                })
                // Execute the afterProcessing hooks for fetching data
                .thenCompose(fetchWeatherDataHook::afterProcessing)
                 // Execute the beforeProcessing hooks for enhancing data
                .thenCompose(enhanceWeatherDataHook::beforeProcessing)
                // Execute the afterProcessing hooks for enhancing data
                .thenCompose(enhanceWeatherDataHook::afterProcessing)
                .thenCompose(returnWeatherDataHook::beforeProcessing)
                .thenCompose(returnWeatherDataHook::afterProcessing)
                 // Return the updated context
                .thenApply(this::getResultForecast);
    }

    // This method is used to extract the result from the context. Seperated in case want to add additional logic
    private Forecast getResultForecast(WeatherDataContext ctx){
        return ctx.getProcessedData();
    }

}
