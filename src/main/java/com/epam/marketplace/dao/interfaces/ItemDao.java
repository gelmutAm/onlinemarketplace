package com.epam.marketplace.dao.interfaces;

import com.epam.marketplace.models.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao {

    void add(Item item);

    void update(Item item);

    void delete(Item item);

    Item getById(int id) throws SQLException;

    List<Item> getAll() throws SQLException;
}
