
package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserValidVenderDetail implements Serializable {

    @SerializedName("VenderId")
    @Expose
    private Integer VenderId;
    @SerializedName("VenderName")
    @Expose
    private String venderName;
    public UserValidVenderDetail(Integer venderId, String venderName) {
        this.VenderId = venderId;
        this.venderName = venderName;
    }

    public Integer getVenderId() {
        return VenderId;
    }

    public void setVenderId(Integer VenderId) {
        this.VenderId = VenderId;
    }

    public String getVenderName() {
        return venderName;
    }

    public void setVenderName(String venderName) {
        this.venderName = venderName;
    }

}
