package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.exceptions.ValidationException;

import java.util.List;

/**
 * Base service needed to interact with DAO.
 *
 * @param <T>
 */
public interface BaseService<T> {

    /**
     * Adds entity.
     *
     * @param entity entity to be added
     * @throws ValidationException if entity object is not valid.
     */
    void add(T entity) throws ValidationException;

    /**
     * Updates entity.
     *
     * @param entity entity to be updated
     * @throws ValidationException if entity object is not valid.
     */
    void update(T entity) throws ValidationException;

    /**
     * Deletes entity.
     *
     * @param entity entity to be deleted
     */
    void delete(T entity);

    /**
     * Returns entity with the specified id.
     *
     * @param id entity id
     * @return entity with the specified id.
     */
    T getById(int id);

    /**
     * Returns all entity objects.
     *
     * @return all entity objects.
     */
    List<T> getAll();
}
