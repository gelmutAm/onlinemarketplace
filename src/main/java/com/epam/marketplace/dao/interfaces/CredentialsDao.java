package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Credentials;

public interface CredentialsDao extends BaseDao<Credentials> {

    Credentials getByLogin(String login);
}
