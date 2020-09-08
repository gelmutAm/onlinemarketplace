package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Bid;

import java.sql.SQLException;
import java.util.List;

public interface BidDao {

    void add(Bid bid) throws SQLException;

    void update(Bid bid) throws SQLException;

    void delete(Bid bid) throws SQLException;

    Bid getById(int id) throws SQLException;

    List<Bid> getAll() throws SQLException;
}
