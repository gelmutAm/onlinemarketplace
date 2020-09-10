package com.epam.marketplace.servlets.api;

import com.epam.marketplace.models.Credentials;
import com.epam.marketplace.models.User;
import com.epam.marketplace.services.interfaces.CredentialsService;
import com.epam.marketplace.services.interfaces.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/api/marketplace/signup")
public class SignUpServlet extends HttpServlet {

    @Inject
    private CredentialsService credentialsService;

    @Inject
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);

        Credentials credentials = new ObjectMapper().readValue(body, Credentials.class);
        try {
            credentialsService.add(credentials);
            int credentialsId = credentialsService.getByLogin(credentials.getLogin()).getId();
            User user = new User();
            user.setCredentialsId(credentialsId);
            userService.add(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
