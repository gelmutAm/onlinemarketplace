package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Bid;

import java.util.List;

public interface BidService<T> extends BaseService<T> {

    int getBidsQtyByItemId(int itemId);

    List<Bid> getAllByUserId(int userId);
}
