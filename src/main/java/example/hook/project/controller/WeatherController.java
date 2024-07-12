package example.hook.project.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import example.hook.project.model.SixteenDayForecaset;
import example.hook.project.service.WeatherDataServiceImpl;

@RestController
@RequestMapping("/weather")
@Validated
public class WeatherController {
    private final WeatherDataServiceImpl weatherDataService;

    public WeatherController(WeatherDataServiceImpl weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    @Operation(summary = "Retrieve 16 Day Forecast for given location")
    @RequestMapping("/forecast")
    public CompletableFuture<ResponseEntity<SixteenDayForecaset>> getForecast(@RequestParam("location") String location) {
        return weatherDataService.fetchWeatherForecast(location).thenApply(ResponseEntity::ok);
                
    }
}
