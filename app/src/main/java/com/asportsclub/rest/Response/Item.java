package com.asportsclub.rest.Response;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

@SerializedName("ItemCode")
@Expose
private Integer itemCode;
@SerializedName("ItemName")
@Expose
private String itemName;
@SerializedName("ItemRate")
@Expose
private Double itemRate;
@SerializedName("TaxDescription")
@Expose
private String taxDescription;
@SerializedName("TaxPercentage")
@Expose
private double taxPercentage;
@SerializedName("ServiceCharge")
@Expose
private double ServiceCharge;
@SerializedName("UnitCode")
@Expose
private Integer UnitCode;



private int itemQuantity;
private boolean itemOrderStatus;
private int orderedQuantity;

public Integer getItemCode() {
return itemCode;
}

public void setItemCode(Integer itemCode) {
this.itemCode = itemCode;
}

public String getItemName() {
return itemName;
}

public void setItemName(String itemName) {
this.itemName = itemName;
}

public Double getItemRate() {
return itemRate;
}

public void setItemRate(Double itemRate) {
this.itemRate = itemRate;
}

public String getTaxDescription() {
return taxDescription;
}

public void setTaxDescription(String taxDescription) {
this.taxDescription = taxDescription;
}

public Double getTaxPercentage() {
return taxPercentage;
}

public void setTaxPercentage(Double taxPercentage) {
this.taxPercentage = taxPercentage;
}

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public double getServiceCharge() {
        return ServiceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        ServiceCharge = serviceCharge;
    }

    public Integer getUnitCode() {
        return UnitCode;
    }

    public void setUnitCode(Integer unitCode) {
        UnitCode = unitCode;
    }

    public boolean isItemOrderStatus() {
        return itemOrderStatus;
    }

    public void setItemOrderStatus(boolean itemOrderStatus) {
        this.itemOrderStatus = itemOrderStatus;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }
}













