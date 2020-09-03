package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Credentials;

import java.util.List;

public interface CredentialsDao {

    void add(Credentials credentials);

    void update(Credentials credentials);

    void delete(Credentials credentials);

    Credentials getById(int id);

    List<Credentials> getAll();
}
