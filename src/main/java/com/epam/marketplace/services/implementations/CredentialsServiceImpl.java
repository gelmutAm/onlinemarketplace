package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.CredentialsDao;
import com.epam.marketplace.exceptions.ValidationException;
import com.epam.marketplace.models.Credentials;
import com.epam.marketplace.models.User;
import com.epam.marketplace.services.interfaces.CredentialsService;
import com.epam.marketplace.services.interfaces.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;

@Service
@Primary
public class CredentialsServiceImpl implements CredentialsService {
    private CredentialsDao credentialsDao;
    private UserService userService;
    private Validator validator;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CredentialsServiceImpl() {
    }

    @Inject
    public CredentialsServiceImpl(CredentialsDao credentialsDao, UserService userService,
                                  Validator validator, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.credentialsDao = credentialsDao;
        this.userService = userService;
        this.validator = validator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void add(Credentials credentials) throws ValidationException {
        if (validator.validate(credentials).isEmpty()) {
            credentials.setPassword(bCryptPasswordEncoder.encode(credentials.getPassword()));
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
