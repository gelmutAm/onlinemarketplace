package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Item;

import java.util.List;

/**
 * Item Data Access Object interface.
 */
public interface ItemDao extends BaseDao<Item> {

    /**
     * Returns all items with the specified seller id.
     *
     * @param id seller id.
     * @return all items with the specified seller id.
     */
    List<Item> getAllBySellerId(int id);
}
