package com.epam.marketplace.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection instance = null;

    private ConnectionManager() throws ClassNotFoundException, SQLException {
        String url = "jdbc:postgresql://localhost:5432/marketplace";
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");

        Class.forName("org.postgresql.Driver");

        instance = DriverManager.getConnection(url, props);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            new ConnectionManager();
        }

        return instance;
    }
}
