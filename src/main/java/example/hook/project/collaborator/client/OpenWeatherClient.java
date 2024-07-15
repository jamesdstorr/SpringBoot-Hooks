package example.hook.project.collaborator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openWeatherClient", url = "${openweather.api.url}")
public interface OpenWeatherClient {
    @GetMapping("/forecast")
    public String getSixteenDayForecaset(@RequestParam("lon") String lon, @RequestParam("lat") String lat, @RequestParam("appid") String apiKey);
}
