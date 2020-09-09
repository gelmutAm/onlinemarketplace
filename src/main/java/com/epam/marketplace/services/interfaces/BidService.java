package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Bid;

import java.sql.SQLException;
import java.util.List;

public interface BidService {

    void add(Bid bid) throws SQLException;

    void update(Bid bid) throws SQLException;

    void delete(Bid bid) throws SQLException;

    Bid getById(int id) throws SQLException;

    int getBidsQtyByItemId(int itemId) throws SQLException;

    List<Bid> getAll() throws SQLException;
}
