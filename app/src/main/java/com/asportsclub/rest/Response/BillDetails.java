
package com.asportsclub.rest.Response;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillDetails implements Serializable {

    @SerializedName("BillNumber")
    @Expose
    private int billNumber;
    @SerializedName("BillTime")
    @Expose
    private String billTime;
    @SerializedName("LocationCode")
    @Expose
    private int locationCode;
    @SerializedName("MembershipDetails")
    @Expose
    private MembershipDetails membershipDetails;
    @SerializedName("WaiterId")
    @Expose
    private int waiterId;
    @SerializedName("TotalAmount")
    @Expose
    private int totalAmount;
    @SerializedName("Usercode")
    @Expose
    private int usercode;
    @SerializedName("TableId")
    @Expose
    private String tableId;
    @SerializedName("RoundingOff")
    @Expose
    private double roundingOff;
    @SerializedName("ItemDetails")
    @Expose
    private List<ItemDetail> itemDetails = null;
    @SerializedName("PAX")
    @Expose
    private int PAX;


    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public int getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(int locationCode) {
        this.locationCode = locationCode;
    }

    public MembershipDetails getMembershipDetails() {
        return membershipDetails;
    }

    public void setMembershipDetails(MembershipDetails membershipDetails) {
        this.membershipDetails = membershipDetails;
    }

    public int getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(int waiterId) {
        this.waiterId = waiterId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getUsercode() {
        return usercode;
    }

    public void setUsercode(int usercode) {
        this.usercode = usercode;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public double getRoundingOff() {
        return roundingOff;
    }

    public void setRoundingOff(double roundingOff) {
        this.roundingOff = roundingOff;
    }

    public List<ItemDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public int getPAX() {
        return PAX;
    }

    public void setPAX(int PAX) {
        this.PAX = PAX;
    }
}
