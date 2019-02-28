package com.asportsclub.rest.RequestModel;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillItem {

@SerializedName("Itemcode")
@Expose
private Integer itemcode;
@SerializedName("UnitCode")
@Expose
private Integer unitCode;
@SerializedName("Quantity")
@Expose
private Integer quantity;
@SerializedName("ItemRate")
@Expose
private Double itemRate;
@SerializedName("Amount")
@Expose
private Double amount;
@SerializedName("Tax")
@Expose
private Double tax;
@SerializedName("ServiceCharge")
@Expose
private Double serviceCharge;
@SerializedName("ItemName")
@Expose
private String itemName;
@SerializedName("UserCode")
@Expose
private Integer userCode;
@SerializedName("LocationCode")
@Expose
private Integer locationCode;

    public BillItem(Integer itemcode, Integer unitCode, Integer quantity, Double itemRate, Double amount, Double tax, Double serviceCharge, String itemName, Integer userCode, Integer locationCode) {
        this.itemcode = itemcode;
        this.unitCode = unitCode;
        this.quantity = quantity;
        this.itemRate = itemRate;
        this.amount = amount;
        this.tax = tax;
        this.serviceCharge = serviceCharge;
        this.itemName = itemName;
        this.userCode = userCode;
        this.locationCode = locationCode;
    }

    public Integer getItemcode() {
return itemcode;
}

public void setItemcode(Integer itemcode) {
this.itemcode = itemcode;
}

public Integer getUnitCode() {
return unitCode;
}

public void setUnitCode(Integer unitCode) {
this.unitCode = unitCode;
}

public Integer getQuantity() {
return quantity;
}

public void setQuantity(Integer quantity) {
this.quantity = quantity;
}

public Double getItemRate() {
return itemRate;
}

public void setItemRate(Double itemRate) {
this.itemRate = itemRate;
}

public Double getAmount() {
return amount;
}

public void setAmount(Double amount) {
this.amount = amount;
}

public Double getTax() {
return tax;
}

public void setTax(Double tax) {
this.tax = tax;
}

public Double getServiceCharge() {
return serviceCharge;
}

public void setServiceCharge(Double serviceCharge) {
this.serviceCharge = serviceCharge;
}

public String getItemName() {
return itemName;
}

public void setItemName(String itemName) {
this.itemName = itemName;
}

public Integer getUserCode() {
return userCode;
}

public void setUserCode(Integer userCode) {
this.userCode = userCode;
}

public Integer getLocationCode() {
return locationCode;
}

public void setLocationCode(Integer locationCode) {
this.locationCode = locationCode;
}

}


