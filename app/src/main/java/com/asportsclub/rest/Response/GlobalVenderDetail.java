
package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GlobalVenderDetail implements Serializable {

    @SerializedName("VenderId")
    @Expose
    private Integer venderId;
    @SerializedName("VenderName")
    @Expose
    private String venderName;

    public GlobalVenderDetail(Integer venderId, String venderName) {
        this.venderId = venderId;
        this.venderName = venderName;
    }

    public Integer getVenderId() {
        return venderId;
    }

    public void setVenderId(Integer venderId) {
        this.venderId = venderId;
    }

    public String getVenderName() {
        return venderName;
    }

    public void setVenderName(String venderName) {
        this.venderName = venderName;
    }

}
