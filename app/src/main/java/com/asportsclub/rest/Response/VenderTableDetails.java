
package com.asportsclub.rest.Response;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VenderTableDetails implements Serializable {

    @SerializedName("StatusCode")
    @Expose
    private StatusCode statusCode;
    @SerializedName("VenderTableDetails")
    @Expose
    private List<VenderTableDetail> venderTableDetails = null;

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public List<VenderTableDetail> getVenderTableDetails() {
        return venderTableDetails;
    }

    public void setVenderTableDetails(List<VenderTableDetail> venderTableDetails) {
        this.venderTableDetails = venderTableDetails;
    }

}
