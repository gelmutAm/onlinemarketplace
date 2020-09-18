package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Credentials;

public interface CredentialsService extends BaseService<Credentials> {

    Credentials getByLogin(String login);
}
