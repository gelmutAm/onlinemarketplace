package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.CredentialsDao;
import com.epam.marketplace.models.Credentials;
import com.epam.marketplace.services.interfaces.CredentialsService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CredentialsServiceImpl implements CredentialsService {

    @Inject
    private CredentialsDao credentialsDao;

    public CredentialsServiceImpl() {
    }

    @Override
    public void add(Credentials credentials) {
        credentialsDao.add(credentials);
    }

    @Override
    public void update(Credentials credentials) {
        credentialsDao.update(credentials);
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
