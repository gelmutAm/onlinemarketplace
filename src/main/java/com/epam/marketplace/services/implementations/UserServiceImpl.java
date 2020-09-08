package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.UserDao;
import com.epam.marketplace.dao.implementations.UserDaoImpl;
import com.epam.marketplace.models.User;
import com.epam.marketplace.services.interfaces.UserService;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    public UserServiceImpl() {
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
        return userDao.getAll();
    }
}
