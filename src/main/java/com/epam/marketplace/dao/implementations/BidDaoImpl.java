package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.BasicConnectionPool;
import com.epam.marketplace.common.ConnectionPool;
import com.epam.marketplace.dao.interfaces.BidDao;
import com.epam.marketplace.models.Bid;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BidDaoImpl implements BidDao {
    private static final String TABLE_NAME = "marketplace.bids";
    private static final String ID_COLUMN_NAME = "bid_id";
    private static final String USER_ID_COLUMN_NAME = "user_id";
    private static final String ITEM_ID_COLUMN_NAME = "item_id";
    private static final String PRICE_COLUMN_NAME = "bid_price";

    private final ConnectionPool connectionPool;

    public BidDaoImpl() throws SQLException {
        connectionPool = BasicConnectionPool.getInstance();
    }

    @Override
    public void add(Bid bid) throws SQLException {
        String query = "insert into " +
                TABLE_NAME +
                " (" + USER_ID_COLUMN_NAME + ", " + ITEM_ID_COLUMN_NAME + ", " + PRICE_COLUMN_NAME + ")" +
                " values (?, ?, ?)";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bid.getUserId());
            preparedStatement.setInt(2, bid.getItemId());
            preparedStatement.setInt(3, bid.getPrice());
            preparedStatement.executeUpdate();
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void update(Bid bid) throws SQLException {
        String query = "update " +
                TABLE_NAME +
                " set  " + USER_ID_COLUMN_NAME + " = ?, " +
                ITEM_ID_COLUMN_NAME + " = ?, " +
                PRICE_COLUMN_NAME + " = ? " +
                " where " + ID_COLUMN_NAME + " = ?";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bid.getUserId());
            preparedStatement.setInt(2, bid.getItemId());
            preparedStatement.setInt(3, bid.getPrice());
            preparedStatement.executeUpdate();
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void delete(Bid bid) throws SQLException {
        String query = "delete from " +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bid.getId());
            preparedStatement.executeUpdate();
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public Bid getById(int id) throws SQLException {
        String query = "select * from" +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        Connection connection = connectionPool.getConnection();
        Bid bid = new Bid();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bid.setId(resultSet.getInt(ID_COLUMN_NAME));
                bid.setUserId(resultSet.getInt(USER_ID_COLUMN_NAME));
                bid.setItemId(resultSet.getInt(ITEM_ID_COLUMN_NAME));
                bid.setPrice(resultSet.getInt(PRICE_COLUMN_NAME));
            }
        }
        connectionPool.releaseConnection(connection);

        return bid;
    }

    @Override
    public int getBidsQtyByItemId(int itemId) throws SQLException {
        String query = "select count(*) from " +
                TABLE_NAME +
                " where " + ITEM_ID_COLUMN_NAME + " = ?";
        Connection connection = connectionPool.getConnection();
        int bidsQty = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bidsQty = resultSet.getInt(1);
            }
        }
        connectionPool.releaseConnection(connection);

        return bidsQty;
    }

    @Override
    public List<Bid> getAll() throws SQLException {
        String query = "select * from " + TABLE_NAME;
        Connection connection = connectionPool.getConnection();
        List<Bid> bids = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Bid bid = new Bid();
                bid.setId(resultSet.getInt(ID_COLUMN_NAME));
                bid.setUserId(resultSet.getInt(USER_ID_COLUMN_NAME));
                bid.setItemId(resultSet.getInt(ITEM_ID_COLUMN_NAME));
                bid.setPrice(resultSet.getInt(PRICE_COLUMN_NAME));

                bids.add(bid);
            }
        }
        connectionPool.releaseConnection(connection);

        return bids;
    }
}
