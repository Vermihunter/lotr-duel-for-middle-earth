package vermesa.lotr.serialization.utils;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(JsonConfigsProvider.class)
public @interface JsonConfigs {
    /**
     * The target config‐class to instantiate from JSON
     */
    Class<?> value();

    /**
     * The resource path or file name of the JSON
     */
    String resource();
}
