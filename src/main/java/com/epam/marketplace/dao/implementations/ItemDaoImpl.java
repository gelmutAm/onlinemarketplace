package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.BasicConnectionPool;
import com.epam.marketplace.common.ConnectionPool;
import com.epam.marketplace.dao.interfaces.ItemDao;
import com.epam.marketplace.models.Item;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItemDaoImpl implements ItemDao {
    private static final String TABLE_NAME = "marketplace.items";
    private static final String ID_COLUMN_NAME = "item_id";
    private static final String SELLER_ID_COLUMN_NAME = "seller_id";
    private static final String ITEM_NAME_COLUMN_NAME = "item_name";
    private static final String ITEM_DESCRIPTION_COLUMN_NAME = "item_description";
    private static final String START_PRICE_COLUMN_NAME = "start_price";
    private static final String STOP_DATE_COLUMN_NAME = "stop_date";
    private static final String PICTURE_LINK_COLUMN_NAME = "picture_link";

    private final ConnectionPool connectionPool;

    public ItemDaoImpl() throws SQLException {
        connectionPool = BasicConnectionPool.getInstance();
    }

    @Override
    public void add(Item item) throws SQLException {
        String query = "insert into " +
                TABLE_NAME +
                " (" + SELLER_ID_COLUMN_NAME + ", " + ITEM_NAME_COLUMN_NAME + ", " + ITEM_DESCRIPTION_COLUMN_NAME + ", " +
                START_PRICE_COLUMN_NAME + ", " + STOP_DATE_COLUMN_NAME + ", " + PICTURE_LINK_COLUMN_NAME + ")" +
                " values (?, ?, ?, ?, ?, ?)";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, item.getSellerId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setInt(4, item.getStartPrice());
            preparedStatement.setString(5, item.getStopDate());
            preparedStatement.setString(6, item.getPictureLink());
            preparedStatement.executeUpdate();
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void update(Item item) throws SQLException {
        String query = "update " +
                TABLE_NAME +
                " set  " + SELLER_ID_COLUMN_NAME + " = ?, " +
                ITEM_NAME_COLUMN_NAME + " = ?, " +
                ITEM_DESCRIPTION_COLUMN_NAME + " = ? " +
                START_PRICE_COLUMN_NAME + " = ? " +
                STOP_DATE_COLUMN_NAME + " = ? " +
                PICTURE_LINK_COLUMN_NAME + " = ? " +
                " where " + ID_COLUMN_NAME + " = ?";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, item.getSellerId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setInt(4, item.getStartPrice());
            preparedStatement.setString(5, item.getStopDate());
            preparedStatement.setString(6, item.getPictureLink());
            preparedStatement.setInt(7, item.getId());
            preparedStatement.executeUpdate();
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public void delete(Item item) throws SQLException {
        String query = "delete from " +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, item.getId());
            preparedStatement.executeUpdate();
        }
        connectionPool.releaseConnection(connection);
    }

    @Override
    public Item getById(int id) throws SQLException {
        String query = "select * from " +
                TABLE_NAME +
                " where " + ID_COLUMN_NAME + " = ?";
        Connection connection = connectionPool.getConnection();
        Item item = new Item();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                item.setId(resultSet.getInt(ID_COLUMN_NAME));
                item.setSellerId(resultSet.getInt(SELLER_ID_COLUMN_NAME));
                item.setName(resultSet.getString(ITEM_NAME_COLUMN_NAME));
                item.setDescription(resultSet.getString(ITEM_DESCRIPTION_COLUMN_NAME));
                item.setStartPrice(resultSet.getInt(START_PRICE_COLUMN_NAME));
                item.setStopDate(resultSet.getString(STOP_DATE_COLUMN_NAME));
                item.setPictureLink(resultSet.getString(PICTURE_LINK_COLUMN_NAME));
            }
        }
        connectionPool.releaseConnection(connection);

        return item;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        String query = "select * from " + TABLE_NAME;
        Connection connection = connectionPool.getConnection();
        List<Item> items = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(ID_COLUMN_NAME));
                item.setSellerId(resultSet.getInt(SELLER_ID_COLUMN_NAME));
                item.setName(resultSet.getString(ITEM_NAME_COLUMN_NAME));
                item.setDescription(resultSet.getString(ITEM_DESCRIPTION_COLUMN_NAME));
                item.setStartPrice(resultSet.getInt(START_PRICE_COLUMN_NAME));
                item.setStopDate(resultSet.getString(STOP_DATE_COLUMN_NAME));
                item.setPictureLink(resultSet.getString(PICTURE_LINK_COLUMN_NAME));

                items.add(item);
            }
        }
        connectionPool.releaseConnection(connection);

        return items;
    }
}