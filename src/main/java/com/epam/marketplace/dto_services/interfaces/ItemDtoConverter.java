package com.epam.marketplace.dto_services.interfaces;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.models.Item;

import java.util.List;

/**
 * Item Data Transfer Object converter interface.
 */
public interface ItemDtoConverter {

    /**
     * Returns {@code ItemDto} from the specified item.
     *
     * @param item item to be converted to {@code ItemDto}
     * @return {@code ItemDto} from the specified item.
     */
    ItemDto itemToDto(Item item);

    /**
     * Returns {@code List} of {@code ItemDto} from the specified {@code List} of {@code Item}.
     *
     * @param items {@code List} of {@code Item} to be converted.
     * @return {@code List} of {@code ItemDto} from the specified {@code List} of {@code Item}.
     */
    List<ItemDto> allItemsToDtos(List<Item> items);
}
