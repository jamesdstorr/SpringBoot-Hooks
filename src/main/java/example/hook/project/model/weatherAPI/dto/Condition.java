package example.hook.project.model.weatherAPI.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Condition {
    @JsonProperty("text")
    String text;
    @JsonProperty("icon")
    String icon;
    @JsonProperty("code")
    String code;
}
