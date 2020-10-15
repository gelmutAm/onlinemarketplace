package com.epam.marketplace.controllers.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <code>AuthorizationController</code> routes authorization requests.
 */
@RestController
@RequestMapping("/api/marketplace/authorization")
public class AuthorizationController {

    /**
     * Sends the specified {@code HttpServletResponse} status depending on authentication information.
     *
     * @param authentication      authentication information about user
     * @param httpServletResponse response to be sent
     */
    @GetMapping
    public void getAuthorized(Authentication authentication, HttpServletResponse httpServletResponse) {
        if (authentication == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
