package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.ItemDao;
import com.epam.marketplace.exceptions.ValidationException;
import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.interfaces.ItemService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;

@ApplicationScoped
public class ItemServiceImpl implements ItemService {

    @Inject
    private ItemDao itemDao;

    @Inject
    private Validator validator;

    public ItemServiceImpl() {
    }

    @Override
    public void add(Item item) throws ValidationException {
        if (validator.validate(item).isEmpty()) {
            itemDao.add(item);
        } else {
            throw new ValidationException();
        }
    }

    @Override
    public void update(Item item) throws ValidationException {
        if (validator.validate(item).isEmpty()) {
            itemDao.update(item);
        } else {
            throw new ValidationException();
        }
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
