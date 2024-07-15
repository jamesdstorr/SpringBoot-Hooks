package example.hook.project.collaborator.weatherAPI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-api", url="${weather.api.url}")
public interface WeatherAPIClient {
    @GetMapping("/forecast.json")
    public String getWeatherForecast(@RequestParam("q") String location, @RequestParam("days") String days, @RequestParam("key") String key );

}
