package com.epam.marketplace.servlets.api;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.dtoservices.interfaces.ItemDtoService;
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

@WebServlet("/api/marketplace/item/*")
public class ItemServlet extends HttpServlet {

    @Inject
    private ItemService itemService;

    @Inject
    private ItemDtoService itemDtoService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = getIdFromPath(req.getPathInfo());
        Item item = null;
        ItemDto itemDto = null;
        try {
            item = itemService.getById(id);
            itemDto = itemDtoService.itemToDto(item);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        resp.setContentType("application/json");
        resp.getWriter().write(new ObjectMapper().writeValueAsString(itemDto));
    }

    private int getIdFromPath(String path) {
        String[] pathParts = path.split("/");
        return Integer.parseInt(pathParts[pathParts.length - 1]);
    }
}
