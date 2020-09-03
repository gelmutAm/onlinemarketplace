package com.epam.marketplace.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/marketplace";
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "root");

        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(url, props);
    }
}
