package com.epam.marketplace.dao.interfaces;

public interface UserDao<T> extends BaseDao<T> {

    T getByCredentialsId(int id);
}
