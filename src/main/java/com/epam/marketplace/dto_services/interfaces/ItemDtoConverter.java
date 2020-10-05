package com.epam.marketplace.dto_services.interfaces;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.models.Item;

import java.util.List;

public interface ItemDtoConverter {

    ItemDto itemToDto(Item item);

    List<ItemDto> allItemsToDtos(List<Item> items);
}
