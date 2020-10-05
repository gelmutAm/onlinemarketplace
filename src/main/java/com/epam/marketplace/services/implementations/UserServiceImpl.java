package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.UserDao;
import com.epam.marketplace.exceptions.ValidationException;
import com.epam.marketplace.models.User;
import com.epam.marketplace.services.interfaces.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;

@Service
@Primary
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private Validator validator;

    public UserServiceImpl() {
    }

    @Inject
    public UserServiceImpl(UserDao userDao, Validator validator) {
        this.userDao = userDao;
        this.validator = validator;
    }

    @Override
    public void add(User user) throws ValidationException {
        if (validator.validate(user).isEmpty()) {
            userDao.add(user);
        } else {
            throw new ValidationException();
        }
    }

    @Override
    public void update(User user) throws ValidationException {
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
