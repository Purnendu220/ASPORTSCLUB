
package com.asportsclub.rest.Response;

import java.io.Serializable;
import java.util.List;

public class GlobalVenderDetails implements Serializable {


    private StatusCode statusCode;

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
