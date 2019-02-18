
package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemDetail implements Serializable {

    @SerializedName("ItemCode")
    @Expose
    private int itemCode;
    @SerializedName("ItemName")
    @Expose
    private String itemName;
    @SerializedName("UnitCode")
    @Expose
    private int unitCode;
    @SerializedName("ItemRate")
    @Expose
    private double itemRate;
    @SerializedName("Quantity")
    @Expose
    private int quantity;
    @SerializedName("ItemAmount")
    @Expose
    private double itemAmount;
    @SerializedName("VatRate")
    @Expose
    private int vatRate;
    @SerializedName("VatAmount")
    @Expose
    private double vatAmount;
    @SerializedName("TotalAmount")
    @Expose
    private double totalAmount;

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(int unitCode) {
        this.unitCode = unitCode;
    }

    public double getItemRate() {
        return itemRate;
    }

    public void setItemRate(double itemRate) {
        this.itemRate = itemRate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public int getVatRate() {
        return vatRate;
    }

    public void setVatRate(int vatRate) {
        this.vatRate = vatRate;
    }

    public double getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(double vatAmount) {
        this.vatAmount = vatAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
