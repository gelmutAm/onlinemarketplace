package com.epam.marketplace.servlets.api;

import com.epam.marketplace.models.Credentials;
import com.epam.marketplace.services.interfaces.CredentialsService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/api/marketplace/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private CredentialsService credentialsService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);

        Credentials requestCredentials = new ObjectMapper().readValue(body, Credentials.class);
        Credentials actualCredentials = null;
        try {
            actualCredentials = credentialsService.getByLogin(requestCredentials.getLogin());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (actualCredentials != null) {
            if (requestCredentials.getPassword().equals(actualCredentials.getPassword())) {
                resp.setStatus(200);
            } else {
                resp.setStatus(401);
            }
        } else {
            resp.setStatus(401);
        }
    }
}
