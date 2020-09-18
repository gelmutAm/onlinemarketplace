package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.dao.interfaces.BaseDao;
import com.epam.marketplace.models.Item;

import java.util.List;

public interface ItemService extends BaseDao<Item> {

    List<Item> getAllBySellerId(int id);
}
