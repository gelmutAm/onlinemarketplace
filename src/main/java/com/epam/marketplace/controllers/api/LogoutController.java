package com.epam.marketplace.controllers.api;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/marketplace/signout")
public class LogoutController {

    @PostMapping
    public void signOut(Authentication authentication, HttpServletResponse httpServletResponse) {
        authentication.setAuthenticated(false);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    }
}
