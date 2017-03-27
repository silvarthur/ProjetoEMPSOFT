package com.example.projetoempsoft.models;

/**
 * Created by nathan on 3/26/17.
 */

public class Item {

    public int itemID;
    public String itemTitle;
    public Double itemPrice;
    public String itemDescription;

    public Item(String itemTitle, String itemDescription, Double itemPrice){
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}
