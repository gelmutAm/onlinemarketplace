package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Item;

import java.util.List;

public interface ItemService extends BaseService<Item> {

    List<Item> getAllBySellerId(int id);
}
