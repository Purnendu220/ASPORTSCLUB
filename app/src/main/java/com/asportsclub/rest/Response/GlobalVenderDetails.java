
package com.asportsclub.rest.Response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GlobalVenderDetails {

    @SerializedName("StatusCode")
    @Expose
    private StatusCode statusCode;
    @SerializedName("GlobalVenderDetails")
    @Expose
    private List<GlobalVenderDetail> globalVenderDetails = null;

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public List<GlobalVenderDetail> getGlobalVenderDetails() {
        return globalVenderDetails;
    }

    public void setGlobalVenderDetails(List<GlobalVenderDetail> globalVenderDetails) {
        this.globalVenderDetails = globalVenderDetails;
    }

}
