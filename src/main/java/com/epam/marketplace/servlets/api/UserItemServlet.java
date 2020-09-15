package com.epam.marketplace.servlets.api;

import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.interfaces.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/api/marketplace/user/item")
public class UserItemServlet extends HttpServlet {

    @Inject
    private ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.write(req.getSession().getAttribute("userId").toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);

        Item item = new ObjectMapper().readValue(body, Item.class);
        item.setSellerId(Integer.parseInt(req.getSession().getAttribute("userId").toString()));
        try {
            itemService.add(item);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
