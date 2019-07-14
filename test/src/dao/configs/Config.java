package dao.configs;

import dao.exceptions.DBConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;

public class Config {

    public static Connection getConnection() throws DBConnectionException {
        ConfigurationManager configurationManager = new ConfigurationManager();
        try {
            String driverName = configurationManager.getProperty(ConfigurationManager.DATABASE_DRIVER_NAME);
            Class.forName(driverName);
            final String url = configurationManager.getProperty(ConfigurationManager.DATABASE_URL);
            String user = configurationManager.getProperty(ConfigurationManager.USER_NAME);
            String password = configurationManager.getProperty(ConfigurationManager.PASSWORD);
            return DriverManager.getConnection(url, user, password);
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            throw new DBConnectionException();
        }
    }
}
