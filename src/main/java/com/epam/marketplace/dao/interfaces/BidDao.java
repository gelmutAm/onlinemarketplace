package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Bid;

import java.util.List;

/**
 * Bid Data Access Object interface.
 */
public interface BidDao extends BaseDao<Bid> {

    /**
     * Returns bids quantity with the specified item id.
     *
     * @param itemId id of the item
     * @return bids quantity with the specified item id.
     */
    int getBidsQtyByItemId(int itemId);

    /**
     * Returns all bids with the specified user id.
     *
     * @param userId id of the user
     * @return all bids with the specified user id.
     */
    List<Bid> getAllByUserId(int userId);
}
