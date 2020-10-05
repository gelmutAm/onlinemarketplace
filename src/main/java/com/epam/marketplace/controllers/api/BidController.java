package com.epam.marketplace.controllers.api;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.dto_services.interfaces.ItemDtoConverter;
import com.epam.marketplace.exceptions.ValidationException;
import com.epam.marketplace.models.Bid;
import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.interfaces.BidService;
import com.epam.marketplace.services.interfaces.ItemService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/marketplace/bid/user")
public class BidController {
    private static final String SESSION_ATTRIBUTE_NAME = "userId";

    private BidService bidService;
    private ItemService itemService;
    private ItemDtoConverter itemDtoConverter;

    public BidController() {
    }

    @Inject
    public BidController(BidService bidService, ItemService itemService, ItemDtoConverter itemDtoConverter) {
        this.bidService = bidService;
        this.itemService = itemService;
        this.itemDtoConverter = itemDtoConverter;
    }

    @GetMapping
    public List<ItemDto> getAllUserBids(HttpSession httpSession) {
        int userId = Integer.parseInt(httpSession.getAttribute(SESSION_ATTRIBUTE_NAME).toString());
        List<Bid> userBids = bidService.getAllByUserId(userId);
        List<Item> items = new ArrayList<>();
        if (userBids != null) {
            for (Bid bid : userBids) {
                Item item = itemService.getById(bid.getItemId());
                items.add(item);
            }
        }

        List<ItemDto> itemDtos = itemDtoConverter.allItemsToDtos(items);

        return itemDtos;
    }

    @PostMapping
    public void makeBid(@RequestBody Bid bid, HttpSession httpSession) {
        int userId = Integer.parseInt(httpSession.getAttribute(SESSION_ATTRIBUTE_NAME).toString());
        bid.setUserId(userId);
        try {
            bidService.add(bid);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
}
