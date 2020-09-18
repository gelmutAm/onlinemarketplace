package com.epam.marketplace.servlets.api;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.dto_services.interfaces.ItemDtoConverter;
import com.epam.marketplace.models.Bid;
import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.interfaces.BidService;
import com.epam.marketplace.services.interfaces.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/marketplace/user/bid")
public class BidServlet extends HttpServlet {

    @Inject
    private BidService<Bid> bidService;

    @Inject
    private ItemService<Item> itemService;

    @Inject
    private ItemDtoConverter itemDtoConverter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
        List<Bid> userBids = bidService.getAllByUserId(userId);

        List<Item> items = new ArrayList<>();
        if (userBids != null) {
            for (Bid bid : userBids) {
                Item item = itemService.getById(bid.getItemId());
                items.add(item);
            }
        }

        List<ItemDto> itemDtos = itemDtoConverter.allItemsToDtos(items);

        resp.setContentType("application/json");
        resp.getWriter().write(new ObjectMapper().writeValueAsString(itemDtos));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);

        Bid bid = new ObjectMapper().readValue(body, Bid.class);
        int userId = Integer.parseInt(req.getSession().getAttribute("userId").toString());
        bid.setUserId(userId);
        bidService.add(bid);
        Item item = itemService.getById(bid.getItemId());
        item.setCurrentPrice(bid.getPrice());
        itemService.update(item);
    }
}
