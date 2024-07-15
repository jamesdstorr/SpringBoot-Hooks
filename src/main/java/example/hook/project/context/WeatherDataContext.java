package example.hook.project.context;

import example.hook.project.model.weatherAPI.dto.Forecast;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherDataContext {

    private String location;
    private String lonLat;
    private String rawData;
    private Forecast processedData;
    private Throwable error;
}
