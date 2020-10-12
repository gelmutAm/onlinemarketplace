package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.User;

/**
 * User Data Access Object interface.
 */
public interface UserDao extends BaseDao<User> {

    /**
     * Returns user with the specified credentials id.
     *
     * @param id credentials id
     * @return user with the specified credentials id.
     */
    User getByCredentialsId(int id);
}
