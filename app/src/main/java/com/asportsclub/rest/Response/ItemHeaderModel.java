package com.asportsclub.rest.Response;

import com.brandongogetap.stickyheaders.exposed.StickyHeader;

public class ItemHeaderModel implements StickyHeader {

    String itemName;
    String itemPrice;
    String itemQuantity;
    String itemGst;
    String itemFinalPrice;

    public ItemHeaderModel(String itemName, String itemPrice, String itemQuantity, String itemGst, String itemFinalPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.itemGst = itemGst;
        this.itemFinalPrice = itemFinalPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemGst() {
        return itemGst;
    }

    public void setItemGst(String itemGst) {
        this.itemGst = itemGst;
    }

    public String getItemFinalPrice() {
        return itemFinalPrice;
    }

    public void setItemFinalPrice(String itemFinalPrice) {
        this.itemFinalPrice = itemFinalPrice;
    }
}
