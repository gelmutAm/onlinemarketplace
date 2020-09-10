package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.ConnectionManager;
import com.epam.marketplace.dao.interfaces.CredentialsDao;
import com.epam.marketplace.models.Credentials;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CredentialsDaoImpl implements CredentialsDao {
    private static final String tableName = "marketplace.credentials";
    private static final String idColumnName = "credentials_id";
    private static final String loginColumnName = "credentials_login";
    private static final String passwordColumnName = "credentials_password";

    private final Connection connection;

    public CredentialsDaoImpl() throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public void add(Credentials credentials) throws SQLException {
        String query = "insert into " +
                tableName +
                " (" + loginColumnName + ", " + passwordColumnName + ")" +
                " values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, credentials.getLogin());
        preparedStatement.setString(2, credentials.getPassword());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Credentials credentials) throws SQLException {
        String query = "update " +
                tableName +
                " set " + loginColumnName + " = ?, " +
                passwordColumnName + " = ? " +
                "where " + idColumnName + " = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, credentials.getLogin());
        preparedStatement.setString(2, credentials.getPassword());
        preparedStatement.setInt(3, credentials.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Credentials credentials) throws SQLException {
        String query = "delete from" +
                tableName +
                " where " + idColumnName + " = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, credentials.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public Credentials getById(int id) throws SQLException {
        String query = "select * from " +
                tableName +
                " where " + idColumnName + " = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Credentials credentials = new Credentials();
        while (resultSet.next()) {
            credentials.setId(resultSet.getInt(idColumnName));
            credentials.setLogin(resultSet.getString(loginColumnName));
            credentials.setPassword(resultSet.getString(passwordColumnName));
        }

        return credentials;
    }

    @Override
    public Credentials getByLogin(String login) throws SQLException {
        String query = "select * from " +
                tableName +
                " where " + loginColumnName + " = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        Credentials credentials = new Credentials();
        while (resultSet.next()) {
            credentials.setId(resultSet.getInt(idColumnName));
            credentials.setLogin(resultSet.getString(loginColumnName));
            credentials.setPassword(resultSet.getString(passwordColumnName));
        }

        return credentials;
    }

    @Override
    public List<Credentials> getAll() throws SQLException {
        String query = "select * from " + tableName;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Credentials> credentialsList = new ArrayList<>();
        while (resultSet.next()) {
            Credentials credentials = new Credentials();
            credentials.setId(resultSet.getInt(idColumnName));
            credentials.setLogin(resultSet.getString(loginColumnName));
            credentials.setPassword(resultSet.getString(passwordColumnName));

            credentialsList.add(credentials);
        }

        return credentialsList;
    }
}
