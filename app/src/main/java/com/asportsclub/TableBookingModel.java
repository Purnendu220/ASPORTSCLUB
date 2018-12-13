package com.asportsclub;

public class TableBookingModel {

    private int id;
    private int tableNumber;
    private boolean tableStatus;
    private String tableName;

    public TableBookingModel(int id, int tableNumber, boolean tableStatus, String tableName) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.tableStatus = tableStatus;
        this.tableName = tableName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public boolean isTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(boolean tableStatus) {
        this.tableStatus = tableStatus;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
