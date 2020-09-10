package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.CredentialsDao;
import com.epam.marketplace.models.Credentials;
import com.epam.marketplace.services.interfaces.CredentialsService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class CredentialsServiceImpl implements CredentialsService {

    @Inject
    private CredentialsDao credentialsDao;

    public CredentialsServiceImpl() {
    }

    @Override
    public void add(Credentials credentials) throws SQLException {
        credentialsDao.add(credentials);
    }

    @Override
    public void update(Credentials credentials) throws SQLException {
        credentialsDao.update(credentials);
    }

    @Override
    public void delete(Credentials credentials) throws SQLException {
        credentialsDao.delete(credentials);
    }

    @Override
    public Credentials getById(int id) throws SQLException {
        return credentialsDao.getById(id);
    }

    @Override
    public Credentials getByLogin(String login) throws SQLException {
        return credentialsDao.getByLogin(login);
    }

    @Override
    public List<Credentials> getAll() throws SQLException {
        return credentialsDao.getAll();
    }
}
