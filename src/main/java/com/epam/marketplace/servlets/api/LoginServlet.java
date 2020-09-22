package com.epam.marketplace.servlets.api;

import com.epam.marketplace.models.Credentials;
import com.epam.marketplace.services.interfaces.CredentialsService;
import com.epam.marketplace.services.interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/marketplace/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private CredentialsService credentialsService;

    @Inject
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);

        Credentials requestCredentials = new ObjectMapper().readValue(body, Credentials.class);
        Credentials actualCredentials = credentialsService.getByLogin(requestCredentials.getLogin());

        if (requestCredentials.getPassword().equals(actualCredentials.getPassword())) {
            HttpSession oldSession = req.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }

            HttpSession newSession = req.getSession(true);

            Integer userId = userService.getByCredentialsId(actualCredentials.getId()).getId();

            newSession.setAttribute("userId", userId);
            newSession.setMaxInactiveInterval(24 * 60 * 60);

            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
