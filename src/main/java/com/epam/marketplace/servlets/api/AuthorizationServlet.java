package com.epam.marketplace.servlets.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/marketplace/authorization")
public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(false);

        if (httpSession == null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
