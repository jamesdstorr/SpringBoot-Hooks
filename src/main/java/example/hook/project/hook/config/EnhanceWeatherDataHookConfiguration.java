package example.hook.project.hook.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.hook.project.context.WeatherDataContext;
import example.hook.project.hook.CompositeWeatherDataHook;
import example.hook.project.hook.WeatherDataHook;
import example.hook.project.hook.annotations.EnhanceWeatherDataHooks;

@Configuration
public class EnhanceWeatherDataHookConfiguration {

    @Bean
    @EnhanceWeatherDataHooks
    public WeatherDataHook<WeatherDataContext> enhanceWeatherDataHook(@EnhanceWeatherDataHooks List<WeatherDataHook<WeatherDataContext>> hooks) {
        return new CompositeWeatherDataHook<>(hooks);
    }
}

