package com.epam.marketplace.dao.interfaces;

import java.util.List;

/**
 * Base Data Access Object interface.
 *
 * @param <T> entity type
 */
public interface BaseDao<T> {

    /**
     * Adds entity.
     *
     * @param entity entity to be added
     */
    void add(T entity);

    /**
     * Updates entity.
     *
     * @param entity entity to be updated
     */
    void update(T entity);

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
     * @return entity with the specified id or {@code null} if entity with this id doesn't exist.
     */
    T getById(int id);

    /**
     * Returns all entity objects.
     *
     * @return all entity objects.
     */
    List<T> getAll();
}
