package com.epam.marketplace.dto_services.implementations;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.dto_services.interfaces.ItemDtoConverter;
import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.interfaces.BidService;
import com.epam.marketplace.services.interfaces.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItemDtoConverterImpl implements ItemDtoConverter {

    @Inject
    private UserService userService;

    @Inject
    private BidService bidService;


    public ItemDtoConverterImpl() {
    }

    public ItemDto itemToDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setSeller(userService.getById(item.getSellerId()).getName());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setStartPrice(item.getStartPrice());
        itemDto.setCurrentPrice(item.getCurrentPrice());
        itemDto.setStopDate(item.getStopDate());
        itemDto.setPictureLink(item.getPictureLink());
        itemDto.setBidsQty(bidService.getBidsQtyByItemId(item.getId()));

        return itemDto;
    }

    @Override
    public List<ItemDto> allItemsToDtos(List<Item> items) {
        List<ItemDto> itemDtos = new ArrayList<>();
        for (Item item : items) {
            itemDtos.add(itemToDto(item));
        }

        return itemDtos;
    }
}
