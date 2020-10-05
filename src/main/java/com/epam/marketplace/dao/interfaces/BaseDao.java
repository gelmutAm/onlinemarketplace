package com.epam.marketplace.dao.interfaces;

import java.util.List;

public interface BaseDao<T> {

    void add(T entity);

    void update(T entity);

    void delete(T entity);

    T getById(int id);

    List<T> getAll();
}
