
package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillDetails {

    @SerializedName("StatusCode")
    @Expose
    private StatusCode statusCode;
    @SerializedName("BillDetails")
    @Expose
    private BillDetails_ billDetails;

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public BillDetails_ getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(BillDetails_ billDetails) {
        this.billDetails = billDetails;
    }

}
