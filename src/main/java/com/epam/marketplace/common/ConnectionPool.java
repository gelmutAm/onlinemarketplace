package com.epam.marketplace.common;

import java.sql.Connection;
import java.util.List;
import java.util.Stack;

public interface ConnectionPool {

    Connection getConnection();

    boolean releaseConnection(Connection connection);

    Stack<Connection> getFreeConnections();

    List<Connection> getUsedConnections();

    String getUrl();

    String getUser();

    String getPassword();

    int getSize();
}
