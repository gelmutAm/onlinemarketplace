package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.ConnectionManager;
import com.epam.marketplace.dao.interfaces.ItemDao;
import com.epam.marketplace.models.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    private final Connection connection;

    public ItemDaoImpl() throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public void add(Item item) {

    }

    @Override
    public void update(Item item) {

    }

    @Override
    public void delete(Item item) {

    }

    @Override
    public Item getById(int id) throws SQLException {
        String query = "select * from marketplace.items where item_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Item item = new Item();
        while (resultSet.next()) {
            item.setId(resultSet.getInt("item_id"));
            item.setSellerId(resultSet.getInt("seller_id"));
            item.setName(resultSet.getString("item_name"));
            item.setDescription(resultSet.getString("item_description"));
            item.setStartPrice(resultSet.getInt("start_price"));
            item.setStopDate(resultSet.getString("stop_date"));
            item.setPictureLink(resultSet.getString("picture_link"));
        }

        return item;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        String query = "select * from marketplace.items";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Item> items = new ArrayList<>();
        while (resultSet.next()) {
            Item item = new Item();
            item.setId(resultSet.getInt("item_id"));
            item.setSellerId(resultSet.getInt("seller_id"));
            item.setName(resultSet.getString("item_name"));
            item.setDescription(resultSet.getString("item_description"));
            item.setStartPrice(resultSet.getInt("start_price"));
            item.setStopDate(resultSet.getString("stop_date"));
            item.setPictureLink(resultSet.getString("picture_link"));

            items.add(item);
        }

        return items;
    }
}
