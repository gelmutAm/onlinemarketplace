package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.BasicConnectionPool;
import com.epam.marketplace.common.ConnectionPool;
import com.epam.marketplace.dao.interfaces.UserDao;
import com.epam.marketplace.models.User;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserDaoImpl implements UserDao {
    private static final String TABLE_NAME = "marketplace.users";
    private static final String ID_COLUMN_NAME = "user_id";
    private static final String NAME_COLUMN_NAME = "user_name";
    private static final String SURNAME_COLUMN_NAME = "user_surname";
    private static final String CREDENTIALS_ID_COLUMN_NAME = "credentials_id";

    private final ConnectionPool connectionPool;

    public UserDaoImpl() throws SQLException {
        connectionPool = BasicConnectionPool.getInstance();
    }

    @Override
    public void add(User user) throws SQLException {
        String query = "insert into " +
                TABLE_NAME +
                " (" + NAME_COLUMN_NAME + ", " + SURNAME_COLUMN_NAME + ", " + CREDENTIALS_ID_COLUMN_NAME + ")" +
                " values (?, ?, ?)";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getCredentialsId());
            preparedStatement.executeUpdate();
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void update(User user) throws SQLException {
        String query = "update " +
                TABLE_NAME +
                " set  " + NAME_COLUMN_NAME + " = ?, " +
                SURNAME_COLUMN_NAME + " = ?, " +
                CREDENTIALS_ID_COLUMN_NAME + " = ? " +
                " where " + ID_COLUMN_NAME + " = ?";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getCredentialsId());
            preparedStatement.executeUpdate();
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void delete(User user) throws SQLException {
        String query = "delete from " +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public User getById(int id) throws SQLException {
        String query = "select * from " +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        Connection connection = connectionPool.getConnection();
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(ID_COLUMN_NAME));
                user.setName(resultSet.getString(NAME_COLUMN_NAME));
                user.setSurname(resultSet.getString(SURNAME_COLUMN_NAME));
                user.setCredentialsId(resultSet.getInt(CREDENTIALS_ID_COLUMN_NAME));
            }
        }
        connectionPool.releaseConnection(connection);

        return user;
    }

    @Override
    public User getByCredentialsId(int id) throws SQLException {
        String query = "select * from " +
                TABLE_NAME +
                " where " + CREDENTIALS_ID_COLUMN_NAME + " = ?";
        Connection connection = connectionPool.getConnection();
        User user = new User();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(ID_COLUMN_NAME));
                user.setName(resultSet.getString(NAME_COLUMN_NAME));
                user.setSurname(resultSet.getString(SURNAME_COLUMN_NAME));
                user.setCredentialsId(resultSet.getInt(CREDENTIALS_ID_COLUMN_NAME));
            }
        }
        connectionPool.releaseConnection(connection);

        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        String query = "select * " + TABLE_NAME;
        Connection connection = connectionPool.getConnection();
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ID_COLUMN_NAME));
                user.setName(resultSet.getString(NAME_COLUMN_NAME));
                user.setSurname(resultSet.getString(SURNAME_COLUMN_NAME));
                user.setCredentialsId(resultSet.getInt(CREDENTIALS_ID_COLUMN_NAME));
                users.add(user);
            }
        }
        connectionPool.releaseConnection(connection);

        return users;
    }
}
