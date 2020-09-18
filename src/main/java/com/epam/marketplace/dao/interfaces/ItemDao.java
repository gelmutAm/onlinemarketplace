package com.epam.marketplace.dao.interfaces;

import java.util.List;

public interface ItemDao<T> extends BaseDao<T> {

    List<T> getAllBySellerId(int id);
}
