package com.epam.marketplace.servlets.api;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.dto_services.interfaces.ItemDtoService;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/api/marketplace/all-items")
public class AllItemsServlet extends HttpServlet {

    @Inject
    private ItemService itemService;

    @Inject
    private ItemDtoService itemDtoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> items = null;
        List<ItemDto> itemDtos = null;
        try {
            items = itemService.getAll();
            itemDtos = itemDtoService.allItemsToDtos(items);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.setContentType("application/json");
        resp.getWriter().write(new ObjectMapper().writeValueAsString(itemDtos));
    }
}
