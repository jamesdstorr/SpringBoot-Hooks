package example.hook.project.model;

import java.util.List;

import lombok.Data;

@Data
public class DayForecast {
    private String date;
    private String sunrise;
    private String sunset;
    private Temperature temperature;
    private String pressure;
    private String humidity;
    private String windSpeed;   
    private String gustSpeed;
    private String cloudiness;
    private List<WeatherSummary> weather;
    
}
