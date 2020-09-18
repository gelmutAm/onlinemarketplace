package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Credentials;

public interface CredentialsService<T> extends BaseService<T> {

    Credentials getByLogin(String login);
}
