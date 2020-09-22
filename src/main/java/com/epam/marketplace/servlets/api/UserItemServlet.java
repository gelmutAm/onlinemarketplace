package com.epam.marketplace.servlets.api;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.dto_services.interfaces.ItemDtoConverter;
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
import java.util.List;

@WebServlet("/api/marketplace/user/item")
public class UserItemServlet extends HttpServlet {

    @Inject
    private ItemService itemService;

    @Inject
    private ItemDtoConverter itemDtoConverter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sellerId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
        List<Item> items = itemService.getAllBySellerId(sellerId);
        List<ItemDto> itemDtos = itemDtoConverter.allItemsToDtos(items);

        resp.setContentType("application/json");
        resp.getWriter().write(new ObjectMapper().writeValueAsString(itemDtos));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);

        Item item = new ObjectMapper().readValue(body, Item.class);
        int userId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
        item.setSellerId(userId);
        item.setCurrentPrice(item.getStartPrice());
        itemService.add(item);
    }
}
