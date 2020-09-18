package com.epam.marketplace.dao.interfaces;

public interface CredentialsDao<T> extends BaseDao<T> {

    T getByLogin(String login);
}
