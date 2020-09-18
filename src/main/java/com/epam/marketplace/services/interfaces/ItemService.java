package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.dao.interfaces.BaseDao;

import java.util.List;

public interface ItemService<T> extends BaseDao<T> {

    List<T> getAllBySellerId(int id);
}
