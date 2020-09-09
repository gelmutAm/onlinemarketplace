package com.epam.marketplace.dto;

public class ItemDto {
    private int id;
    private String seller;
    private String name;
    private String description;
    private int startPrice;
    private String stopDate;
    private String pictureLink;
    private int bidsQty;

    public ItemDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public int getBidsQty() {
        return bidsQty;
    }

    public void setBidsQty(int bidsQty) {
        this.bidsQty = bidsQty;
    }
}
