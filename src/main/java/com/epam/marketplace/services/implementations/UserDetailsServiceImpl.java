package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.CredentialsDao;
import com.epam.marketplace.dao.interfaces.UserDao;
import com.epam.marketplace.models.Credentials;
import com.epam.marketplace.models.User;
import com.epam.marketplace.models.UserDetailsImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserDao userDao;
    private CredentialsDao credentialsDao;

    public UserDetailsServiceImpl() {
    }

    @Inject
    public UserDetailsServiceImpl(UserDao userDao, CredentialsDao credentialsDao) {
        this.userDao = userDao;
        this.credentialsDao = credentialsDao;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Credentials credentials = credentialsDao.getByLogin(login);
        User user = null;
        if (credentials != null) {
            user = userDao.getByCredentialsId(credentials.getId());
        } else {
            throw new UsernameNotFoundException(login);
        }

        return new UserDetailsImpl(user, credentials);
    }
}
