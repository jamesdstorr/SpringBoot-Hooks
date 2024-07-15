package example.hook.project.model.weatherAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {

    @JsonProperty("last_updated")
    String lastUpdated;
    @JsonProperty("temp_c")
    String temperature;
    @JsonProperty("current")
    Current current;
    @JsonProperty("wind_mph")
    String windSpeed;
    @JsonProperty("wind_dir")
    String windDirection;
}
