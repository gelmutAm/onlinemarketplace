package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.UserDao;
import com.epam.marketplace.models.User;
import com.epam.marketplace.services.interfaces.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    public UserServiceImpl() {
    }

    @Override
    public void add(User user) throws SQLException {
        userDao.add(user);
    }

    @Override
    public void update(User user) throws SQLException {
        userDao.update(user);
    }

    @Override
    public void delete(User user) throws SQLException {
        userDao.delete(user);
    }

    @Override
    public User getById(int id) throws SQLException {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() throws SQLException {
        return userDao.getAll();
    }
}
