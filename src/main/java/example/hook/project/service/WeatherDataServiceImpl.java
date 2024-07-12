package example.hook.project.service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import example.hook.project.collaborator.client.OpenWeatherClient;
import example.hook.project.context.WeatherDataContext;
import example.hook.project.hook.WeatherDataHook;
import example.hook.project.hook.WeatherDataHookEnum;
import example.hook.project.model.SixteenDayForecaset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataServiceImpl {
    private final OpenWeatherClient openWeatherClient; //Feign Client 
    private final WeatherDataHook<WeatherDataContext> fetchWeatherDataHook; //Composite Hok for fetching Weather Data
    private final WeatherDataHook<WeatherDataContext> enhanceWeatherDataHook; //Composite Hok for enhancing Weather Data

    @Value("${openweather.api.key}")
    private String apiKey;

    public WeatherDataServiceImpl(OpenWeatherClient openWeatherClient,
            Map<WeatherDataHookEnum, WeatherDataHook<WeatherDataContext>> weatherDataHookMap) {
        this.openWeatherClient = openWeatherClient;
        // Retrieve the fetch weather data hook from the map using the enum
        this.fetchWeatherDataHook = weatherDataHookMap.get(WeatherDataHookEnum.FETCH_WEATHER_DATA);
        // Retrieve the enhance weather data hook from the map using the enum
        this.enhanceWeatherDataHook = weatherDataHookMap.get(WeatherDataHookEnum.ENHANCE_WEATHER_DATA);
    }

    public CompletableFuture<SixteenDayForecaset> fetchWeatherForecast(String location) {
        // Create a WeatherDataContext with the given location
        WeatherDataContext weatherDataContext = WeatherDataContext.builder().location(location).build();

        // Execute the beforeProcessing hooks for fetching data
        return fetchWeatherDataHook.beforeProcessing(weatherDataContext)
                .thenCompose(ctx -> {
                    try {
                        // Call the OpenWeather API using the Feign client
                        String rawData = openWeatherClient.getSixteenDayForecaset(ctx.getLon(), ctx.getLat(), apiKey);
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
                 // Return the updated context
                .thenApply(this::getResultForecast);
               
    }

    // This method is used to extract the result from the context. Seperated in case want to add additional logic
    private SixteenDayForecaset getResultForecast(WeatherDataContext ctx){
        return ctx.getProcessedData();
    }

}
