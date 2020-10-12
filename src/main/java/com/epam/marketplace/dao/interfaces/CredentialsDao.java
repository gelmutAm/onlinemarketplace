package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Credentials;

/**
 * Credentials Data Access Object interface.
 */
public interface CredentialsDao extends BaseDao<Credentials> {

    /**
     * Returns credentials with the specified login.
     *
     * @param login user login
     * @return credentials with the specified login.
     */
    Credentials getByLogin(String login);
}
