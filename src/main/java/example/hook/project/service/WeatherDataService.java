package example.hook.project.service;

import java.util.concurrent.CompletableFuture;

import example.hook.project.model.weatherAPI.dto.Forecast;


public interface WeatherDataService {
    CompletableFuture<Forecast> fetchSixteenDayForecast(String location);
}
