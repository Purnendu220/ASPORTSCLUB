
package com.asportsclub.rest.Response;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthenticateUserResponse implements Serializable {

    @SerializedName("StatusCode")
    @Expose
    private StatusCode statusCode;
    @SerializedName("BusinessDate")
    @Expose
    private String businessDate;
    @SerializedName("UserDetail")
    @Expose
    private Object userDetail;
    @SerializedName("UserValidVenderDetails")
    @Expose
    private List<UserValidVenderDetail> userValidVenderDetails = null;
    @SerializedName("VenderTableDetails")
    @Expose
    private List<VenderTableDetail> venderTableDetails = null;

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public Object getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(Object userDetail) {
        this.userDetail = userDetail;
    }

    public List<UserValidVenderDetail> getUserValidVenderDetails() {
        return userValidVenderDetails;
    }

    public void setUserValidVenderDetails(List<UserValidVenderDetail> userValidVenderDetails) {
        this.userValidVenderDetails = userValidVenderDetails;
    }

    public List<VenderTableDetail> getVenderTableDetails() {
        return venderTableDetails;
    }

    public void setVenderTableDetails(List<VenderTableDetail> venderTableDetails) {
        this.venderTableDetails = venderTableDetails;
    }

}
