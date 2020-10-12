package com.epam.marketplace.controllers.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/marketplace/authorization")
public class AuthorizationController {

    @GetMapping
    public void getAuthorized(Authentication authentication, HttpServletResponse httpServletResponse) {
        if (authentication == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
