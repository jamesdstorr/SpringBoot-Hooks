package example.hook.project.model.weatherAPI.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {
    @JsonProperty("maxtemp_c")
    String maxtemp_c;
    @JsonProperty("mintemp_c")
    String mintemp_c;
    @JsonProperty("avgtemp_c")
    String avgtemp_c;
    @JsonProperty("maxwind_mph")
    String maxwind_mph;
    @JsonProperty("condition")
    Condition condition;
}
