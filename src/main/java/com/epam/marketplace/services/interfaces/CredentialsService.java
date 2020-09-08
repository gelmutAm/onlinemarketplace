package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Credentials;

import java.sql.SQLException;
import java.util.List;

public interface CredentialsService {

    void add(Credentials credentials) throws SQLException;

    void update(Credentials credentials) throws SQLException;

    void delete(Credentials credentials) throws SQLException;

    Credentials getById(int id) throws SQLException;

    List<Credentials> getAll() throws SQLException;
}
