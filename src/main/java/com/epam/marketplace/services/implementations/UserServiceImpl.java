package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.UserDao;
import com.epam.marketplace.models.User;
import com.epam.marketplace.services.interfaces.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.List;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Inject
    private Validator validator;

    public UserServiceImpl() {
    }

    @Override
    public void add(User user) {
        if (validator.validate(user).isEmpty()) {
            userDao.add(user);
        } else {
            throw new ValidationException();
        }
    }

    @Override
    public void update(User user) {
        if (validator.validate(user).isEmpty()) {
            userDao.update(user);
        } else {
            throw new ValidationException();
        }
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
