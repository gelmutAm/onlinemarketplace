package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.BasicConnectionPool;
import com.epam.marketplace.common.ConnectionPool;
import com.epam.marketplace.dao.interfaces.CredentialsDao;
import com.epam.marketplace.models.Credentials;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CredentialsDaoImpl implements CredentialsDao {
    private static final String TABLE_NAME = "marketplace.credentials";
    private static final String ID_COLUMN_NAME = "credentials_id";
    private static final String LOGIN_COLUMN_NAME = "credentials_login";
    private static final String PASSWORD_COLUMN_NAME = "credentials_password";

    private final ConnectionPool connectionPool;

    public CredentialsDaoImpl() throws SQLException {
        connectionPool = BasicConnectionPool.getInstance();
    }

    @Override
    public void add(Credentials credentials) {
        String query = "insert into " +
                TABLE_NAME +
                " (" + LOGIN_COLUMN_NAME + ", " + PASSWORD_COLUMN_NAME + ")" +
                " values (?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, credentials.getLogin());
            preparedStatement.setString(2, credentials.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Credentials credentials) {
        String query = "update " +
                TABLE_NAME +
                " set " + LOGIN_COLUMN_NAME + " = ?, " +
                PASSWORD_COLUMN_NAME + " = ? " +
                "where " + ID_COLUMN_NAME + " = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, credentials.getLogin());
            preparedStatement.setString(2, credentials.getPassword());
            preparedStatement.setInt(3, credentials.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Credentials credentials) {
        String query = "delete from" +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, credentials.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Credentials getById(int id) {
        String query = "select * from " +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        Credentials credentials = new Credentials();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                credentials.setId(resultSet.getInt(ID_COLUMN_NAME));
                credentials.setLogin(resultSet.getString(LOGIN_COLUMN_NAME));
                credentials.setPassword(resultSet.getString(PASSWORD_COLUMN_NAME));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return credentials;
    }

    @Override
    public Credentials getByLogin(String login) {
        String query = "select * from " +
                TABLE_NAME +
                " where " + LOGIN_COLUMN_NAME + " = ?";
        Credentials credentials = new Credentials();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                credentials.setId(resultSet.getInt(ID_COLUMN_NAME));
                credentials.setLogin(resultSet.getString(LOGIN_COLUMN_NAME));
                credentials.setPassword(resultSet.getString(PASSWORD_COLUMN_NAME));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return credentials;
    }

    @Override
    public List<Credentials> getAll() {
        String query = "select * from " + TABLE_NAME;
        List<Credentials> credentialsList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Credentials credentials = new Credentials();
                credentials.setId(resultSet.getInt(ID_COLUMN_NAME));
                credentials.setLogin(resultSet.getString(LOGIN_COLUMN_NAME));
                credentials.setPassword(resultSet.getString(PASSWORD_COLUMN_NAME));

                credentialsList.add(credentials);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return credentialsList;
    }
}
