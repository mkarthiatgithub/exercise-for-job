package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private Properties properties = new Properties();

    public PropertyReader() {
        loadProperties();
    }

    private void loadProperties() {
        try {
            InputStream inputStream = new FileInputStream("src/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("File NOT found or can not read fix it " + e.getMessage());
        }
    }

    public String readProperty(String key) {
        return properties.getProperty(key);
    }

    public String propertyNotValidMsg(String key, String value) {
        return "*** Key (" + key + "," + value + ") specified in src/config.properties is not valid ***";
    }
}
