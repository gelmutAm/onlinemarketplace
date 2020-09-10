package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Credentials;

import java.sql.SQLException;
import java.util.List;

public interface CredentialsDao {

    void add(Credentials credentials) throws SQLException;

    void update(Credentials credentials) throws SQLException;

    void delete(Credentials credentials) throws SQLException;

    Credentials getById(int id) throws SQLException;

    Credentials getByLogin(String login) throws SQLException;

    List<Credentials> getAll() throws SQLException;
}
