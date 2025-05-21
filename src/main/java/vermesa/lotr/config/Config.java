package vermesa.lotr.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();
    private static final String DEFAULT_CONFIG_FILE = "config.properties";

    public Config(String configFile) {
        initialize(configFile);
    }

    public Config() {
        initialize(DEFAULT_CONFIG_FILE);
    }

    private void initialize(String configFile) {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(configFile)) {
            if (in == null) {
                throw new IllegalStateException("config.properties not found");
            }
            props.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public String get(String key) {
        return props.getProperty(key);
    }
}