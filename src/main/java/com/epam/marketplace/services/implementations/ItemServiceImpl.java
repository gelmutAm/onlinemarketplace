package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.ItemDao;
import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.interfaces.ItemService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class ItemServiceImpl implements ItemService {

    @Inject
    private ItemDao itemDao;

    public ItemServiceImpl() {
    }

    @Override
    public void add(Item item) throws SQLException {
        itemDao.add(item);
    }

    @Override
    public void update(Item item) throws SQLException {
        itemDao.update(item);
    }

    @Override
    public void delete(Item item) throws SQLException {
        itemDao.delete(item);
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
