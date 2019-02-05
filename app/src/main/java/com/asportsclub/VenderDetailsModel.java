package com.asportsclub;

import java.io.Serializable;

public class VenderDetailsModel implements Serializable {
    public Integer VenderId;
    public String VenderName;

    public VenderDetailsModel(Integer venderId, String venderName) {
        VenderId = venderId;
        VenderName = venderName;
    }

    public Integer getVenderId() {
        return VenderId;
    }

    public void setVenderId(Integer venderId) {
        VenderId = venderId;
    }

    public String getVenderName() {
        return VenderName;
    }

    public void setVenderName(String venderName) {
        VenderName = venderName;
    }
}
