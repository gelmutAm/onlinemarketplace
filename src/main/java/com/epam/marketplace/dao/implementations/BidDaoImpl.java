package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.BasicConnectionPool;
import com.epam.marketplace.common.ConnectionPool;
import com.epam.marketplace.dao.interfaces.BidDao;
import com.epam.marketplace.models.Bid;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
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
    public void add(Bid bid) {
        String query = "insert into " +
                TABLE_NAME +
                " (" + USER_ID_COLUMN_NAME + ", " + ITEM_ID_COLUMN_NAME + ", " + PRICE_COLUMN_NAME + ")" +
                " values (?, ?, ?)";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bid.getUserId());
            preparedStatement.setInt(2, bid.getItemId());
            preparedStatement.setInt(3, bid.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Bid bid) {
        String query = "update " +
                TABLE_NAME +
                " set  " + USER_ID_COLUMN_NAME + " = ?, " +
                ITEM_ID_COLUMN_NAME + " = ?, " +
                PRICE_COLUMN_NAME + " = ? " +
                " where " + ID_COLUMN_NAME + " = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bid.getUserId());
            preparedStatement.setInt(2, bid.getItemId());
            preparedStatement.setInt(3, bid.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Bid bid) {
        String query = "delete from " +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bid.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Bid getById(int id) {
        String query = "select * from " +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        Bid bid = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    bid = new Bid();
                    bid.setId(resultSet.getInt(ID_COLUMN_NAME));
                    bid.setUserId(resultSet.getInt(USER_ID_COLUMN_NAME));
                    bid.setItemId(resultSet.getInt(ITEM_ID_COLUMN_NAME));
                    bid.setPrice(resultSet.getInt(PRICE_COLUMN_NAME));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bid;
    }

    @Override
    public int getBidsQtyByItemId(int itemId) {
        String query = "select count(*) from " +
                TABLE_NAME +
                " where " + ITEM_ID_COLUMN_NAME + " = ?";
        int bidsQty = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, itemId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    bidsQty = resultSet.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bidsQty;
    }

    @Override
    public List<Bid> getAllByUserId(int userId) {
        String query = "select " + USER_ID_COLUMN_NAME + ", " +
                ITEM_ID_COLUMN_NAME + ", " +
                "max(" + PRICE_COLUMN_NAME + ") as " + PRICE_COLUMN_NAME +
                " from " +
                TABLE_NAME +
                " group by " + USER_ID_COLUMN_NAME + ", " +
                ITEM_ID_COLUMN_NAME +
                " having " + USER_ID_COLUMN_NAME + " = ?";
        List<Bid> bids = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Bid bid = new Bid();
                    bid.setUserId(resultSet.getInt(USER_ID_COLUMN_NAME));
                    bid.setItemId(resultSet.getInt(ITEM_ID_COLUMN_NAME));
                    bid.setPrice(resultSet.getInt(PRICE_COLUMN_NAME));

                    bids.add(bid);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bids;
    }

    @Override
    public List<Bid> getAll() {
        String query = "select * from " + TABLE_NAME;
        List<Bid> bids = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    Bid bid = new Bid();
                    bid.setId(resultSet.getInt(ID_COLUMN_NAME));
                    bid.setUserId(resultSet.getInt(USER_ID_COLUMN_NAME));
                    bid.setItemId(resultSet.getInt(ITEM_ID_COLUMN_NAME));
                    bid.setPrice(resultSet.getInt(PRICE_COLUMN_NAME));

                    bids.add(bid);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bids;
    }
}
