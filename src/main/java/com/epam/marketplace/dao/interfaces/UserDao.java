package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void add(User user);

    void update(User user);

    void delete(User user);

    User getById(int id);

    List<String> getAll() throws SQLException;
}
