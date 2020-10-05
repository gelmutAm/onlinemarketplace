package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.BasicConnectionPool;
import com.epam.marketplace.common.ConnectionPool;
import com.epam.marketplace.dao.interfaces.UserDao;
import com.epam.marketplace.models.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
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
    public void add(User user) {
        String query = "insert into " +
                TABLE_NAME +
                " (" + NAME_COLUMN_NAME + ", " + SURNAME_COLUMN_NAME + ", " + CREDENTIALS_ID_COLUMN_NAME + ")" +
                " values (?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getCredentialsId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String query = "update " +
                TABLE_NAME +
                " set  " + NAME_COLUMN_NAME + " = ?, " +
                SURNAME_COLUMN_NAME + " = ?, " +
                CREDENTIALS_ID_COLUMN_NAME + " = ? " +
                " where " + ID_COLUMN_NAME + " = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setInt(3, user.getCredentialsId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        String query = "delete from " +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getById(int id) {
        String query = "select * from " +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt(ID_COLUMN_NAME));
                    user.setName(resultSet.getString(NAME_COLUMN_NAME));
                    user.setSurname(resultSet.getString(SURNAME_COLUMN_NAME));
                    user.setCredentialsId(resultSet.getInt(CREDENTIALS_ID_COLUMN_NAME));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    @Override
    public User getByCredentialsId(int id) {
        String query = "select * from " +
                TABLE_NAME +
                " where " + CREDENTIALS_ID_COLUMN_NAME + " = ?";
        User user = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt(ID_COLUMN_NAME));
                    user.setName(resultSet.getString(NAME_COLUMN_NAME));
                    user.setSurname(resultSet.getString(SURNAME_COLUMN_NAME));
                    user.setCredentialsId(resultSet.getInt(CREDENTIALS_ID_COLUMN_NAME));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        String query = "select * " + TABLE_NAME;
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt(ID_COLUMN_NAME));
                    user.setName(resultSet.getString(NAME_COLUMN_NAME));
                    user.setSurname(resultSet.getString(SURNAME_COLUMN_NAME));
                    user.setCredentialsId(resultSet.getInt(CREDENTIALS_ID_COLUMN_NAME));
                    users.add(user);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }
}
