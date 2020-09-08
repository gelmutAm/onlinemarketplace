package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao {

    void add(Item item) throws SQLException;

    void update(Item item) throws SQLException;

    void delete(Item item) throws SQLException;

    Item getById(int id) throws SQLException;

    List<Item> getAll() throws SQLException;
}
