package com.epam.marketplace.models;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.time.OffsetDateTime;

public class Item {

    private int id;

    @Positive
    private int sellerId;

    @Pattern(regexp = "^[a-zA-Z ]{1,100}$")
    private String name;

    @Pattern(regexp = "^[0-9a-zA-Z ]{1,1000}$")
    private String description;

    @Positive
    private int startPrice;

    @Positive
    private int currentPrice;

    private OffsetDateTime stopDate;

    private String pictureLink;

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(int currentPrice) {
        this.currentPrice = currentPrice;
    }

    public OffsetDateTime getStopDate() {
        return stopDate;
    }

    public void setStopDate(OffsetDateTime stopDate) {
        this.stopDate = stopDate;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
}
