
package com.asportsclub.rest.Response;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpenBillDetails implements Serializable {

    @SerializedName("StatusCode")
    @Expose
    private StatusCode statusCode;
    @SerializedName("OpenBillDetails")
    @Expose
    private List<OpenBillDetail> openBillDetails = null;

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public List<OpenBillDetail> getOpenBillDetails() {
        return openBillDetails;
    }

    public void setOpenBillDetails(List<OpenBillDetail> openBillDetails) {
        this.openBillDetails = openBillDetails;
    }

}
