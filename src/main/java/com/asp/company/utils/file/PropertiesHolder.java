package com.asp.company.utils.file;

import com.asp.company.exception.ConfigurationException;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by serhii on 11/1/16.
 */
public final class PropertiesHolder {

    private static final Properties PROPERTIES = load();

    public static final String APP_PROPERTIES_PATH = "/app.properties";

    private static Properties load() {
        Properties properties = new Properties();
        try {
            properties.load(PropertiesHolder.class.getResourceAsStream(APP_PROPERTIES_PATH));
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConfigurationException(e.getMessage());
        }
        return properties;
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    public static double getDoubleProperty(String key){
        return Double.parseDouble(PROPERTIES.getProperty(key));
    }

    public static int getIntProperty(String key){
        return Integer.parseInt(PROPERTIES.getProperty(key));
    }
}
