package com.epam.marketplace.dto_services.interfaces;

import com.epam.marketplace.dto.ItemDto;
import com.epam.marketplace.models.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDtoService {

    ItemDto itemToDto(Item item) throws SQLException;

    List<ItemDto> allItemsToDtos(List<Item> items) throws SQLException;
}
