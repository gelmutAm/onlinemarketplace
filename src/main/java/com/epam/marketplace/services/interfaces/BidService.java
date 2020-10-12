package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Bid;

import java.util.List;

/**
 * Bid service interface.
 */
public interface BidService extends BaseService<Bid> {

    /**
     * Returns bids quantity with the specified item id.
     *
     * @param itemId item id
     * @return bids quantity with the specified item id.
     */
    int getBidsQtyByItemId(int itemId);

    /**
     * Returns all bids with specified user id.
     *
     * @param userId user id.
     * @return all bids with specified user id.
     */
    List<Bid> getAllByUserId(int userId);
}
