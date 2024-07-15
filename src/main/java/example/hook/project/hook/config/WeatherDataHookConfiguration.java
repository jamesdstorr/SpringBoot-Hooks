package example.hook.project.hook.config;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.hook.project.context.WeatherDataContext;
import example.hook.project.hook.CompositeWeatherDataHook;
import example.hook.project.hook.WeatherDataHook;
import example.hook.project.hook.WeatherDataHookEnum;
import example.hook.project.hook.annotations.EnhanceWeatherDataHooks;
import example.hook.project.hook.annotations.FetchWeatherDataHooks;
import example.hook.project.hook.annotations.ReturnWeatherDataHooks;

@Configuration
public class WeatherDataHookConfiguration {

    @Bean
    public Map<WeatherDataHookEnum, WeatherDataHook<WeatherDataContext>> weatherDataHooks(
        WeatherDataHook<WeatherDataContext> compositeFetchWeatherDataHook,
        WeatherDataHook<WeatherDataContext> compositeEnhanceWeatherDataHook,
        WeatherDataHook<WeatherDataContext> compositeReturnWeatherDataHook) {
        return Map.of(
            WeatherDataHookEnum.FETCH_WEATHER_DATA, compositeFetchWeatherDataHook,
            WeatherDataHookEnum.ENHANCE_WEATHER_DATA, compositeEnhanceWeatherDataHook,
            WeatherDataHookEnum.RETURN_WEATHER_DATA, compositeReturnWeatherDataHook);
    }

    @Bean
    public WeatherDataHook<WeatherDataContext> compositeFetchWeatherDataHook(@FetchWeatherDataHooks List<WeatherDataHook<WeatherDataContext>> hooks) {
        return new CompositeWeatherDataHook<>(hooks);
    }

    @Bean
    public WeatherDataHook<WeatherDataContext> compositeEnhanceWeatherDataHook(@EnhanceWeatherDataHooks List<WeatherDataHook<WeatherDataContext>> hooks) {
        return new CompositeWeatherDataHook<>(hooks);
    }

    @Bean
    public WeatherDataHook<WeatherDataContext> compositeReturnWeatherDataHook(@ReturnWeatherDataHooks List<WeatherDataHook<WeatherDataContext>> hooks) {
        return new CompositeWeatherDataHook<>(hooks);
    }
}
