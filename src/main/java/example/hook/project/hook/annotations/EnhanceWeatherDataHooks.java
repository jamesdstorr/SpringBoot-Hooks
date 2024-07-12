package example.hook.project.hook.annotations;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for marking classes that contain hooks to be executed when enhancing weather data.
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
@Qualifier("EnhanceWeatherDataHooks")
public @interface EnhanceWeatherDataHooks {
}