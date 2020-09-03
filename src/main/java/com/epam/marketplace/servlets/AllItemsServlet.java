package com.epam.marketplace.servlets;

import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.implementations.ItemServiceImpl;
import com.epam.marketplace.services.interfaces.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/marketplace/all-items")
public class AllItemsServlet extends HttpServlet {
    private final ItemService itemService;

    //@Inject
    public AllItemsServlet() throws SQLException, ClassNotFoundException {
        itemService = new ItemServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> items = null;
        try {
            items = itemService.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.setContentType("application/json");
        resp.getWriter().write(new ObjectMapper().writeValueAsString(items));
    }
}
