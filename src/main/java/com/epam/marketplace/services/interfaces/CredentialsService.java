package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Credentials;

/**
 * Credentials service interface.
 */
public interface CredentialsService extends BaseService<Credentials> {

    /**
     * Returns credentials with the specified login.
     *
     * @param login user login
     * @return credentials with the specified login.
     */
    Credentials getByLogin(String login);
}
