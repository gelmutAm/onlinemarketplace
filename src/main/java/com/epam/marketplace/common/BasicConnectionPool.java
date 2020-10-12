package com.epam.marketplace.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Implementation of the {@code ConnectionPool} interface.
 */
public class BasicConnectionPool implements ConnectionPool {
    private static final String URL = "jdbc:postgresql://localhost:5432/marketplace";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final int INITIAL_POOL_SIZE = 30;

    private static BasicConnectionPool instance;

    private Stack<Connection> readyToUseConnections;
    private List<Connection> usedConnections = new ArrayList<>();

    private BasicConnectionPool() throws SQLException {
        readyToUseConnections = new Stack<>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            readyToUseConnections.push(createConnection());
        }
    }

    public static synchronized BasicConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            instance = new BasicConnectionPool();
        }
        return instance;
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public Connection getConnection() {
        Connection connection = readyToUseConnections.pop();
        usedConnections.add(connection);
        return new ConnectionImpl(this, connection);
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        readyToUseConnections.push(connection);
        return usedConnections.remove(connection);
    }

    @Override
    public Stack<Connection> getFreeConnections() {
        return readyToUseConnections;
    }

    @Override
    public List<Connection> getUsedConnections() {
        return usedConnections;
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
        return readyToUseConnections.size() + usedConnections.size();
    }
}
