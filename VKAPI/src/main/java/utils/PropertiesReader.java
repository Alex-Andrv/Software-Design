package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {
    public static Map<String, String> getProperties() {
        try (InputStream input = Files.newInputStream(Paths.get("src/main/resources/config.properties"))) {

            Map<String, String> properties = new HashMap<>();

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value
            properties.put("access_token", prop.getProperty("access_token"));
            properties.put("host", prop.getProperty("host", "api.vk.com"));

            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
