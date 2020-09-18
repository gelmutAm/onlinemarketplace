package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.BidDao;
import com.epam.marketplace.models.Bid;
import com.epam.marketplace.services.interfaces.BidService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BidServiceImpl implements BidService<Bid> {

    @Inject
    private BidDao<Bid> bidDao;

    public BidServiceImpl() {
    }

    @Override
    public void add(Bid bid) {
        bidDao.add(bid);
    }

    @Override
    public void update(Bid bid) {
        bidDao.update(bid);
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
