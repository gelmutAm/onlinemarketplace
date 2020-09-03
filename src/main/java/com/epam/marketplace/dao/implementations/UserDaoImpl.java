package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.ConnectionManager;
import com.epam.marketplace.dao.interfaces.UserDao;
import com.epam.marketplace.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final Connection connection;

    public UserDaoImpl() throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public List<String> getAll() throws SQLException {
        String query = "select * from marketplace.users";
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery(query);
        List<String> names = new ArrayList<>();
        while (set.next()) {
            names.add(set.getString("user_name"));
        }
        return names;
    }
}
