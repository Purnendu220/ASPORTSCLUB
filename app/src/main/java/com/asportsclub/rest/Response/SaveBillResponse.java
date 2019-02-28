package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveBillResponse {

    @SerializedName("StatusCode")
    @Expose
    private StatusCode StatusCode;

    @SerializedName("BillNumber")
    @Expose
    private int BillNumber;
    @SerializedName("TotalAmount")
    @Expose
    private Double TotalAmount;

    public com.asportsclub.rest.Response.StatusCode getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(com.asportsclub.rest.Response.StatusCode statusCode) {
        StatusCode = statusCode;
    }

    public int getBillNumber() {
        return BillNumber;
    }

    public void setBillNumber(int billNumber) {
        BillNumber = billNumber;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }
}
