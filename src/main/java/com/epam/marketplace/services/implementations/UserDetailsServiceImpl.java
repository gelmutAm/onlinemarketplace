package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.CredentialsDao;
import com.epam.marketplace.models.Credentials;
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
    private CredentialsDao credentialsDao;

    public UserDetailsServiceImpl() {
    }

    @Inject
    public UserDetailsServiceImpl(CredentialsDao credentialsDao) {
        this.credentialsDao = credentialsDao;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Credentials credentials = credentialsDao.getByLogin(login);
        if (credentials == null) {
            throw new UsernameNotFoundException(login);
        }

        return new UserDetailsImpl(credentials);
    }
}
