package example.hook.project.model;

import lombok.Data;

@Data
public class WeatherSummary {
    private String id;
    private String main;
    private String description;
    private String icon;
}
