package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Credentials;

import java.util.List;

public interface CredentialsService {

    void add(Credentials credentials);

    void update(Credentials credentials);

    void delete(Credentials credentials);

    Credentials getById(int id);

    List<Credentials> getAll();
}
