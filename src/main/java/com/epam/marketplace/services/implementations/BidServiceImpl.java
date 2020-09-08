package com.epam.marketplace.services.implementations;

import com.epam.marketplace.dao.interfaces.BidDao;
import com.epam.marketplace.models.Bid;
import com.epam.marketplace.services.interfaces.BidService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class BidServiceImpl implements BidService {

    @Inject
    private BidDao bidDao;

    public BidServiceImpl() {
    }

    @Override
    public void add(Bid bid) throws SQLException {
        bidDao.add(bid);
    }

    @Override
    public void update(Bid bid) throws SQLException {
        bidDao.update(bid);
    }

    @Override
    public void delete(Bid bid) throws SQLException {
        bidDao.delete(bid);
    }

    @Override
    public Bid getById(int id) throws SQLException {
        return bidDao.getById(id);
    }

    @Override
    public List<Bid> getAll() throws SQLException {
        return bidDao.getAll();
    }
}
