package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.User;

public interface UserService extends BaseService<User> {

    User getByCredentialsId(int id);
}
