package com.epam.marketplace.controllers.api;

import com.epam.marketplace.models.Credentials;
import com.epam.marketplace.models.User;
import com.epam.marketplace.models.UserDetailsImpl;
import com.epam.marketplace.services.interfaces.CredentialsService;
import com.epam.marketplace.services.interfaces.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/marketplace")
public class LoginController {
    private UserService userService;
    private CredentialsService credentialsService;

    public LoginController() {
    }

    @Inject
    public LoginController(UserService userService, CredentialsService credentialsService) {
        this.userService = userService;
        this.credentialsService = credentialsService;
    }

    @PostMapping("/login")
    public void login(@RequestBody Credentials credentials, HttpSession httpSession) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication();
        Credentials userCredentials = ((UserDetailsImpl) authenticationToken.getPrincipal()).getUserDetails();
        User user = userService.getByCredentialsId(userCredentials.getId());
        int userId = user.getId();
        httpSession.setAttribute("userId", userId);
    }
}
