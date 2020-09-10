package com.epam.marketplace.dao.implementations;

import com.epam.marketplace.common.ConnectionManager;
import com.epam.marketplace.dao.interfaces.ItemDao;
import com.epam.marketplace.models.Item;

import javax.enterprise.context.ApplicationScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItemDaoImpl implements ItemDao {
    private static final String tableName = "marketplace.items";
    private static final String idColumnName = "item_id";
    private static final String sellerIdColumnName = "seller_id";
    private static final String itemNameColumnName = "item_name";
    private static final String itemDescriptionColumnName = "item_description";
    private static final String startPriceColumnName = "start_price";
    private static final String stopDateColumnName = "stop_date";
    private static final String pictureLinkColumnName = "picture_link";

    private final Connection connection;

    public ItemDaoImpl() throws SQLException, ClassNotFoundException {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public void add(Item item) throws SQLException {
        String query = "insert into " +
                tableName +
                " (" + sellerIdColumnName + ", " + itemNameColumnName + ", " + itemDescriptionColumnName + ", " +
                startPriceColumnName + ", " + stopDateColumnName + ", " + pictureLinkColumnName + ")" +
                " values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, item.getSellerId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getDescription());
        preparedStatement.setInt(4, item.getStartPrice());
        preparedStatement.setString(5, item.getStopDate());
        preparedStatement.setString(6, item.getPictureLink());

        preparedStatement.executeUpdate();
    }

    @Override
    public void update(Item item) throws SQLException {
        String query = "update " +
                tableName +
                " set  " + sellerIdColumnName + " = ?, " +
                itemNameColumnName + " = ?, " +
                itemDescriptionColumnName + " = ? " +
                startPriceColumnName + " = ? " +
                stopDateColumnName + " = ? " +
                pictureLinkColumnName + " = ? " +
                " where " + idColumnName + " = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, item.getSellerId());
        preparedStatement.setString(2, item.getName());
        preparedStatement.setString(3, item.getDescription());
        preparedStatement.setInt(4, item.getStartPrice());
        preparedStatement.setString(5, item.getStopDate());
        preparedStatement.setString(6, item.getPictureLink());
        preparedStatement.setInt(7, item.getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Item item) throws SQLException {
        String query = "delete from " +
                tableName +
                " where " + idColumnName + " = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, item.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public Item getById(int id) throws SQLException {
        String query = "select * from " +
                tableName +
                " where " + idColumnName + " = ?";
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
        String query = "select * from " + tableName;
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