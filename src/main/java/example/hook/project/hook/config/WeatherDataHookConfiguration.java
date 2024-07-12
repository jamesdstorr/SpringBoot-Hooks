package example.hook.project.hook.config;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.hook.project.hook.WeatherDataHook;
import example.hook.project.hook.WeatherDataHookEnum;
import example.hook.project.hook.annotations.EnhanceWeatherDataHooks;
import example.hook.project.hook.annotations.FetchWeatherDataHooks;
import example.hook.project.hook.annotations.ReturnWeatherDataHooks;
import example.hook.project.context.WeatherDataContext;
import example.hook.project.hook.CompositeWeatherDataHook;

/**
 * Configuration class that sets up the hooks for different stages of weather data processing.
 */

@Configuration
public class WeatherDataHookConfiguration {

    @Bean
    public Map<WeatherDataHookEnum, WeatherDataHook<WeatherDataContext>> weatherDataHooks(
        @FetchWeatherDataHooks WeatherDataHook<WeatherDataContext> fetchWeatherDataHook,
        @EnhanceWeatherDataHooks WeatherDataHook<WeatherDataContext>  enhancWeatherDataHook,
        @ReturnWeatherDataHooks   WeatherDataHook<WeatherDataContext>  returnWeatherDataHook) {
        return Map.of(
                WeatherDataHookEnum.FETCH_WEATHER_DATA, fetchWeatherDataHook,
                WeatherDataHookEnum.ENHANCE_WEATHER_DATA, enhancWeatherDataHook,
                WeatherDataHookEnum.RETURN_WEATHER_DATA, returnWeatherDataHook);
    }
   
}
