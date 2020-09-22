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

@WebServlet("/api/marketplace/signup")
public class SignUpServlet extends HttpServlet {

    @Inject
    private CredentialsService credentialsService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);

        Credentials credentials = new ObjectMapper().readValue(body, Credentials.class);
        credentialsService.add(credentials);
    }
}
