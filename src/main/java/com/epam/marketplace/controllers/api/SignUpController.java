package com.epam.marketplace.controllers.api;

import com.epam.marketplace.exceptions.ValidationException;
import com.epam.marketplace.models.Credentials;
import com.epam.marketplace.services.interfaces.CredentialsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * <code>SignUpController</code> routes sign up requests.
 */
@Controller
@RequestMapping("/api/marketplace/signup")
public class SignUpController {
    private CredentialsService credentialsService;

    public SignUpController() {
    }

    @Inject
    public SignUpController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    /**
     * Registers user with the specified credentials.
     *
     * @param credentials user credentials
     */
    @PostMapping
    public void addUser(@RequestBody Credentials credentials) {
        try {
            credentialsService.add(credentials);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
}
