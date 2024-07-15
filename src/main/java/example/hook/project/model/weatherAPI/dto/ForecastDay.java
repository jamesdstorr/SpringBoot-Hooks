package example.hook.project.model.weatherAPI.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDay {
    @JsonProperty("date")
    String date;
    @JsonProperty("day")
    Day day;
}
