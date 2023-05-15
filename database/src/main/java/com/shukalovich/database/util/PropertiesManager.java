package com.shukalovich.database.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import java.util.Properties;

@UtilityClass
public class PropertiesManager {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }
    public String get(String key) {
        return PROPERTIES.getProperty(key);
    }
    @SneakyThrows
    private void loadProperties() {
        try (var inputStream = PropertiesManager.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        }
    }
}
