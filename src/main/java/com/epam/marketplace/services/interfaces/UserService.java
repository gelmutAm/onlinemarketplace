package com.epam.marketplace.services.interfaces;

public interface UserService<T> extends BaseService<T> {

    T getByCredentialsId(int id);
}
