package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void add(User user) throws SQLException;

    void update(User user) throws SQLException;

    void delete(User user) throws SQLException;

    User getById(int id) throws SQLException;

    User getByCredentialsId(int id) throws SQLException;

    List<User> getAll() throws SQLException;
}
