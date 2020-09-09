package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.ConnectionManager;
import com.epam.marketplace.dao.interfaces.UserDao;
import com.epam.marketplace.models.User;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserDaoImpl implements UserDao {
    private static final String tableName = "marketplace.users";

    private final Connection connection;

    public UserDaoImpl() throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public void add(User user) throws SQLException {
        String query = "insert into " +
                tableName +
                " (user_name, user_surname, credentials_id)" +
                " values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setInt(3, user.getCredentialsId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(User user) throws SQLException {
        String query = "update " +
                tableName +
                " set user_name = ?," +
                " user_surname = ?," +
                " credentials_id = ?" +
                " where user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setInt(3, user.getCredentialsId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(User user) throws SQLException {
        String query = "delete from " +
                tableName +
                " where user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public User getById(int id) throws SQLException {
        String query = "select * from " +
                tableName +
                " where user_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getInt("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setSurname(resultSet.getString("user_surname"));
            user.setCredentialsId(resultSet.getInt("credentials_id"));
        }

        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        String query = "select * " + tableName;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setSurname(resultSet.getString("user_surname"));
            user.setCredentialsId(resultSet.getInt("credentials_id"));
            users.add(user);
        }

        return users;
    }
}
