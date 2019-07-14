package dao.configs;

import dao.exceptions.DBConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    public static final String DATABASE_DRIVER_NAME = "DATABASE_DRIVER_NAME";
    public static final String DATABASE_URL = "DATABASE_URL";
    public static final String USER_NAME = "USER_NAME";
    public static final String PASSWORD = "PASSWORD";

    public String getProperty(String key) throws DBConfigurationException {
        Properties properties = new Properties();
        String fileName = "config.properties";
        try {
            InputStream in = ConfigurationManager.class.getClassLoader().getResourceAsStream(fileName);
            properties.load(in);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DBConfigurationException();
        }
    }
}
