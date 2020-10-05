package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Bid;

import java.util.List;

public interface BidService extends BaseService<Bid> {

    int getBidsQtyByItemId(int itemId);

    List<Bid> getAllByUserId(int userId);
}
