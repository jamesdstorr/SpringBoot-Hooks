package example.hook.project.model.weatherAPI.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {
    @JsonProperty("forecastday")
    List<ForecastDay> forecastDays;
}
