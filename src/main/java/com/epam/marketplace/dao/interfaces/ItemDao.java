package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Item;

import java.util.List;

public interface ItemDao extends BaseDao<Item> {

    List<Item> getAllBySellerId(int id);
}
