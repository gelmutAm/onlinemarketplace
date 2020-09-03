package com.epam.marketplace.services.interfaces;

import com.epam.marketplace.models.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemService {

    void add(Item item);

    void update(Item item);

    void delete(Item item);

    Item getById(int id) throws SQLException;

    List<Item> getAll() throws SQLException;
}
