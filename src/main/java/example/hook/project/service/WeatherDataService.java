package example.hook.project.service;

import java.util.concurrent.CompletableFuture;
import example.hook.project.model.SixteenDayForecaset;


public interface WeatherDataService {
    CompletableFuture<SixteenDayForecaset> fetchSixteenDayForecast(String location);
}
