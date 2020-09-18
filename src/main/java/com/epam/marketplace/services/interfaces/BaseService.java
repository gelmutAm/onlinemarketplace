package com.epam.marketplace.services.interfaces;

import java.util.List;

public interface BaseService<T> {

    void add(T entity);

    void update(T entity);

    void delete(T entity);

    T getById(int id);

    List<T> getAll();
}
