package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Item;

import java.util.List;

/**
 * Item service interface.
 */
public interface ItemService extends BaseService<Item> {

    /**
     * Returns all items with the specified seller id.
     *
     * @param id seller id
     * @return all items with the specified seller id.
     */
    List<Item> getAllBySellerId(int id);
}
