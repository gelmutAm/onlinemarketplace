package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.CredentialsDao;
import com.epam.marketplace.models.Credentials;
import com.epam.marketplace.models.User;
import com.epam.marketplace.services.interfaces.CredentialsService;
import com.epam.marketplace.services.interfaces.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.List;

@ApplicationScoped
public class CredentialsServiceImpl implements CredentialsService {

    @Inject
    private CredentialsDao credentialsDao;

    @Inject
    private UserService userService;

    @Inject
    private Validator validator;

    public CredentialsServiceImpl() {
    }

    @Override
    public void add(Credentials credentials) {
        if (validator.validate(credentials).isEmpty()) {
            credentialsDao.add(credentials);
            int credentialsId = getByLogin(credentials.getLogin()).getId();
            User user = new User();
            user.setCredentialsId(credentialsId);
            userService.add(user);
        } else {
            throw new ValidationException();
        }
    }

    @Override
    public void update(Credentials credentials) throws ValidationException {
        if (validator.validate(credentials).isEmpty()) {
            credentialsDao.update(credentials);
        } else {
            throw new ValidationException();
        }
    }

    @Override
    public void delete(Credentials credentials) {
        credentialsDao.delete(credentials);
    }

    @Override
    public Credentials getById(int id) {
        return credentialsDao.getById(id);
    }

    @Override
    public Credentials getByLogin(String login) {
        return credentialsDao.getByLogin(login);
    }

    @Override
    public List<Credentials> getAll() {
        return credentialsDao.getAll();
    }
}
