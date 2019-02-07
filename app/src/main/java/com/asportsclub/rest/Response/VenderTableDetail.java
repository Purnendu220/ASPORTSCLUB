
package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VenderTableDetail implements Serializable {

    @SerializedName("TableId")
    @Expose
    private int tableId;
    @SerializedName("TableName")
    @Expose
    private String tableName;
    @SerializedName("TableStatus")
    @Expose
    private int tableStatus;
    @SerializedName("LocationCode")
    @Expose
    private int locationCode;
    @SerializedName("BillNumber")
    @Expose
    private int billNumber;
    @SerializedName("BillLocation")
    @Expose
    private int billLocation;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(int tableStatus) {
        this.tableStatus = tableStatus;
    }

    public int getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(int locationCode) {
        this.locationCode = locationCode;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public int getBillLocation() {
        return billLocation;
    }

    public void setBillLocation(int billLocation) {
        this.billLocation = billLocation;
    }

}
