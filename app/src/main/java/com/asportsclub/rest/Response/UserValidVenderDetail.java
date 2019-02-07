
package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserValidVenderDetail implements Serializable {

    @SerializedName("VenderCode")
    @Expose
    private int venderCode;
    @SerializedName("VenderName")
    @Expose
    private String venderName;

    public int getVenderCode() {
        return venderCode;
    }

    public void setVenderCode(int venderCode) {
        this.venderCode = venderCode;
    }

    public String getVenderName() {
        return venderName;
    }

    public void setVenderName(String venderName) {
        this.venderName = venderName;
    }

}
