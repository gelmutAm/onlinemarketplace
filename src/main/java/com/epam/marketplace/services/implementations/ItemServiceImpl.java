package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.implementations.ItemDaoImpl;
import com.epam.marketplace.dao.interfaces.ItemDao;
import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.interfaces.ItemService;

import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements ItemService {
    private final ItemDao itemDao;

    //@Inject
    public ItemServiceImpl() throws SQLException, ClassNotFoundException {
        itemDao = new ItemDaoImpl();
    }

    @Override
    public void add(Item item) {

    }

    @Override
    public void update(Item item) {

    }

    @Override
    public void delete(Item item) {

    }

    @Override
    public Item getById(int id) throws SQLException {
        return itemDao.getById(id);
    }

    @Override
    public List<Item> getAll() throws SQLException {
        return itemDao.getAll();
    }
}
