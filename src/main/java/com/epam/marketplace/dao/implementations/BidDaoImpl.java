package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.ConnectionManager;
import com.epam.marketplace.dao.interfaces.BidDao;
import com.epam.marketplace.models.Bid;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BidDaoImpl implements BidDao {
    private static final String tableName = "marketplace.bids";

    private final Connection connection;

    public BidDaoImpl() throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public void add(Bid bid) throws SQLException {
        String query = "insert into" +
                tableName +
                " values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, bid.getUserId());
        preparedStatement.setInt(2, bid.getItemId());
        preparedStatement.setInt(3, bid.getPrice());
        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Bid bid) throws SQLException {
        String query = "update " +
                tableName +
                " set user_id = ?," +
                " item_id = ?," +
                " bid_price = ?," +
                " where bid_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, bid.getUserId());
        preparedStatement.setInt(2, bid.getItemId());
        preparedStatement.setInt(3, bid.getPrice());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Bid bid) throws SQLException {
        String query = "delete from " +
                tableName +
                " where bid_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, bid.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public Bid getById(int id) throws SQLException {
        String query = "select * from" +
                tableName +
                " where bid_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Bid bid = new Bid();
        while (resultSet.next()) {
            bid.setId(resultSet.getInt("bid_id"));
            bid.setUserId(resultSet.getInt("user_id"));
            bid.setItemId(resultSet.getInt("item_id"));
            bid.setPrice(resultSet.getInt("bid_price"));
        }

        return bid;
    }

    @Override
    public List<Bid> getAll() throws SQLException {
        String query = "select * from " + tableName;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Bid> bids = new ArrayList<>();
        while (resultSet.next()) {
            Bid bid = new Bid();
            bid.setId(resultSet.getInt("bid_id"));
            bid.setUserId(resultSet.getInt("user_id"));
            bid.setItemId(resultSet.getInt("item_id"));
            bid.setPrice(resultSet.getInt("bid_price"));

            bids.add(bid);
        }

        return bids;
    }
}
