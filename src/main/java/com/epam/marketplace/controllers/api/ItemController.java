package com.epam.marketplace.controllers.api;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.dto_services.interfaces.ItemDtoConverter;
import com.epam.marketplace.exceptions.ValidationException;
import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.interfaces.ItemService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/marketplace/item")
public class ItemController {
    private ItemService itemService;
    private ItemDtoConverter itemDtoConverter;

    public ItemController() {
    }

    @Inject
    public ItemController(ItemService itemService, ItemDtoConverter itemDtoConverter) {
        this.itemService = itemService;
        this.itemDtoConverter = itemDtoConverter;
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@PathVariable int itemId) {
        Item item = itemService.getById(itemId);
        ItemDto itemDto = itemDtoConverter.itemToDto(item);
        return itemDto;
    }

    @GetMapping("/all")
    public List<ItemDto> getAllItems() {
        List<Item> items = itemService.getAll();
        List<ItemDto> itemDtos = itemDtoConverter.allItemsToDtos(items);
        return itemDtos;
    }

    @GetMapping("/user")
    public List<ItemDto> getAllUserItems(HttpSession httpSession) {
        int userId = Integer.parseInt(httpSession.getAttribute("userId").toString());
        List<Item> items = itemService.getAllBySellerId(userId);
        List<ItemDto> itemDtos = itemDtoConverter.allItemsToDtos(items);
        return itemDtos;
    }

    @PostMapping("/user")
    public void addUserItem(@RequestBody Item item, HttpSession httpSession) {
        int userId = Integer.parseInt(httpSession.getAttribute("userId").toString());
        item.setSellerId(userId);
        item.setCurrentPrice(item.getStartPrice());
        try {
            itemService.add(item);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
}
