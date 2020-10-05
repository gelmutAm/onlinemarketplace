package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.exceptions.ValidationException;

import java.util.List;

public interface BaseService<T> {

    void add(T entity) throws ValidationException;

    void update(T entity) throws ValidationException;

    void delete(T entity);

    T getById(int id);

    List<T> getAll();
}
