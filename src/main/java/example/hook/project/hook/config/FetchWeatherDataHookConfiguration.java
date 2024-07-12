package example.hook.project.hook.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.hook.project.context.WeatherDataContext;
import example.hook.project.hook.CompositeWeatherDataHook;
import example.hook.project.hook.WeatherDataHook;
import example.hook.project.hook.annotations.FetchWeatherDataHooks;

@Configuration
public class FetchWeatherDataHookConfiguration {

    @Bean
    @FetchWeatherDataHooks
    public WeatherDataHook<WeatherDataContext> fetchWeatherDataHook(@FetchWeatherDataHooks List<WeatherDataHook<WeatherDataContext>> hooks) {
        return new CompositeWeatherDataHook<>(hooks);
    }
}