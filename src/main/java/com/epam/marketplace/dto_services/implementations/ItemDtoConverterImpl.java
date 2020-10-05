package com.epam.marketplace.dto_services.implementations;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.dto_services.interfaces.ItemDtoConverter;
import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.interfaces.BidService;
import com.epam.marketplace.services.interfaces.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class ItemDtoConverterImpl implements ItemDtoConverter {
    private UserService userService;
    private BidService bidService;

    public ItemDtoConverterImpl() {
    }

    @Inject
    public ItemDtoConverterImpl(UserService userService, BidService bidService) {
        this.userService = userService;
        this.bidService = bidService;
    }

    public ItemDto itemToDto(Item item) {
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setSeller(userService.getById(item.getSellerId()).getName());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setStartPrice(item.getStartPrice());
        itemDto.setCurrentPrice(item.getCurrentPrice());
        itemDto.setStopDate(item.getStopDate().toString());
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
