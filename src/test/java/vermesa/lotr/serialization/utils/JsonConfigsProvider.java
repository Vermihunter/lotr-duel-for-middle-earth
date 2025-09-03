package vermesa.lotr.serialization.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.junit.jupiter.params.support.ParameterDeclarations;
import vermesa.lotr.model.central_board.Region;
import vermesa.lotr.serialization.json.RegionMixIn;

import java.io.InputStream;
import java.util.List;
import java.util.stream.*;

public class JsonConfigsProvider implements ArgumentsProvider, AnnotationConsumer<JsonConfigs> {
    private Class<?> configClass;
    private String resource;

    @Override
    public void accept(JsonConfigs annotation) {
        this.configClass = annotation.value();
        this.resource = annotation.resource();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ParameterDeclarations parameters, ExtensionContext context) throws Exception {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        // 3) Mix-In for Region, so Jackson knows how to call your existing constructor:
        mapper.addMixIn(Region.class, RegionMixIn.class);

        // Now read the JSON array from the classpath (e.g. “/add_units_test_config.json”):
        try (InputStream is = JsonConfigsProvider.class.getResourceAsStream(resource)) {
            if (is == null) {
                throw new IllegalArgumentException("Resource not found: " + resource);
            }

            // 1) read into a List of your config class
            List<?> configs = mapper.readValue(
                    is,
                    mapper.getTypeFactory().constructCollectionType(List.class, configClass)
            );

            // 2) convert to Stream<Arguments>
            return configs.stream().map(Arguments::of);
        }
    }
}
