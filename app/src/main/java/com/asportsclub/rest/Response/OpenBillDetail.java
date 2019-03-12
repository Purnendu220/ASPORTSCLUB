
package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OpenBillDetail implements Serializable {

    @SerializedName("TableName")
    @Expose
    private String tableName;
    @SerializedName("BillNumber")
    @Expose
    private int billNumber;
    @SerializedName("MembershipId")
    @Expose
    private String membershipId;
    @SerializedName("MemberName")
    @Expose
    private String memberName;
    @SerializedName("BillAmount")
    @Expose
    private int billAmount;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String membershipId) {
        this.membershipId = membershipId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }

}
