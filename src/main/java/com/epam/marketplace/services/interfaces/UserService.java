package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.User;

/**
 * User service interface.
 */
public interface UserService extends BaseService<User> {

    /**
     * Returns user with the specified credentials id.
     *
     * @param id credentials id
     * @return user with the specified credentials id.
     */
    User getByCredentialsId(int id);
}
