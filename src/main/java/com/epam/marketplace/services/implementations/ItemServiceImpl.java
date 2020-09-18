package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.ItemDao;
import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.interfaces.ItemService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ItemServiceImpl implements ItemService<Item> {

    @Inject
    private ItemDao<Item> itemDao;

    public ItemServiceImpl() {
    }

    @Override
    public void add(Item item) {
        itemDao.add(item);
    }

    @Override
    public void update(Item item) {
        itemDao.update(item);
    }

    @Override
    public void delete(Item item) {
        itemDao.delete(item);
    }

    @Override
    public Item getById(int id) {
        return itemDao.getById(id);
    }

    @Override
    public List<Item> getAllBySellerId(int id) {
        return itemDao.getAllBySellerId(id);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }
}
