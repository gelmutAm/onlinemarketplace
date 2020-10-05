package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.BidDao;
import com.epam.marketplace.exceptions.ValidationException;
import com.epam.marketplace.models.Bid;
import com.epam.marketplace.models.Item;
import com.epam.marketplace.services.interfaces.BidService;
import com.epam.marketplace.services.interfaces.ItemService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.Validator;
import java.util.List;

@Service
@Primary
public class BidServiceImpl implements BidService {
    private BidDao bidDao;
    private ItemService itemService;
    private Validator validator;

    public BidServiceImpl() {
    }

    @Inject
    public BidServiceImpl(BidDao bidDao, ItemService itemService, Validator validator) {
        this.bidDao = bidDao;
        this.itemService = itemService;
        this.validator = validator;
    }

    @Override
    public void add(Bid bid) throws ValidationException {
        Item item = itemService.getById(bid.getItemId());

        if (validator.validate(bid).isEmpty() && bid.getPrice() > item.getCurrentPrice()) {
            bidDao.add(bid);
            item.setCurrentPrice(bid.getPrice());
            itemService.update(item);
        } else {
            throw new ValidationException();
        }
    }

    @Override
    public void update(Bid bid) throws ValidationException {
        if (validator.validate(bid).isEmpty()) {
            bidDao.update(bid);
        } else {
            throw new ValidationException();
        }
    }

    @Override
    public void delete(Bid bid) {
        bidDao.delete(bid);
    }

    @Override
    public Bid getById(int id) {
        return bidDao.getById(id);
    }

    @Override
    public int getBidsQtyByItemId(int itemId) {
        return bidDao.getBidsQtyByItemId(itemId);
    }

    @Override
    public List<Bid> getAllByUserId(int userId) {
        return bidDao.getAllByUserId(userId);
    }

    @Override
    public List<Bid> getAll() {
        return bidDao.getAll();
    }
}
