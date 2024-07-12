package example.hook.project.context;

import example.hook.project.model.SixteenDayForecaset;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherDataContext {

    private String location;
    private String lon;
    private String lat;
    private String rawData;
    private SixteenDayForecaset processedData;
    private Throwable error;
    
}
