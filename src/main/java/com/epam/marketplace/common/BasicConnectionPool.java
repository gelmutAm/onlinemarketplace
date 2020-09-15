package com.epam.marketplace.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnectionPool implements ConnectionPool {
    private static final String URL = "jdbc:postgresql://localhost:5432/marketplace";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final int INITIAL_POOL_SIZE = 30;

    public static BasicConnectionPool instance;

    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();

    private BasicConnectionPool() throws SQLException {
        connectionPool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            connectionPool.add(createConnection());
        }
    }

    public static synchronized BasicConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            instance = new BasicConnectionPool();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public String getUser() {
        return USER;
    }

    @Override
    public String getPassword() {
        return PASSWORD;
    }

    @Override
    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
}
