package com.epam.marketplace.common;

import java.sql.Connection;
import java.util.List;
import java.util.Stack;

/**
 * Connection pool interface.
 */
public interface ConnectionPool {

    /**
     * Returns the connection with the specified database.
     *
     * @return the connection with the specified database.
     */
    Connection getConnection();

    /**
     * Releases the connection with the specified database.
     *
     * @param connection connection to be released
     * @return {@code true} if connection has released.
     */
    boolean releaseConnection(Connection connection);

    /**
     * Returns free connections of the connection pool.
     *
     * @return stack containing free to use connections of the connection pool.
     */
    Stack<Connection> getFreeConnections();

    /**
     * Returns used connections of the connection pool.
     *
     * @return list containing used connections of the connection pool.
     */
    List<Connection> getUsedConnections();

    /**
     * Returns the database URL.
     *
     * @return url of the specified database.
     */
    String getUrl();

    /**
     * Returns the user of the specified database.
     *
     * @return the user of the specified database.
     */
    String getUser();

    /**
     * Returns the password of the specified database.
     *
     * @return the password of the specified database.
     */
    String getPassword();

    /**
     * Returns the size of the connection pool.
     *
     * @return the size of the connection pool.
     */
    int getSize();
}
