package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Bid;

import java.util.List;

public interface BidDao {

    void add(Bid bid);

    void update(Bid bid);

    void delete(Bid bid);

    Bid getById(int id);

    List<Bid> getAll();
}
