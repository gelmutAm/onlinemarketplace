package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.UserDao;
import com.epam.marketplace.models.User;
import com.epam.marketplace.services.interfaces.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService<User> {

    @Inject
    private UserDao<User> userDao;

    public UserServiceImpl() {
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public User getByCredentialsId(int id) {
        return userDao.getByCredentialsId(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}
