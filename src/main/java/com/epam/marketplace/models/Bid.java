package com.epam.marketplace.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

public class Bid {

    private int id;

    @Positive
    private int userId;

    @Positive
    private int itemId;

    @Positive
    private int price;

    public Bid() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
