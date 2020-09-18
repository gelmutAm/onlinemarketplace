package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Bid;

import java.util.List;

public interface BidDao<T> extends BaseDao<T> {

    int getBidsQtyByItemId(int itemId);

    List<Bid> getAllByUserId(int userId);
}
