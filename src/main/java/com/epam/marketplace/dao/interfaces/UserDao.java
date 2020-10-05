package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.User;

public interface UserDao extends BaseDao<User> {

    User getByCredentialsId(int id);
}
