package com.asportsclub.rest.RequestModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillSaveApi {

@SerializedName("BillNumber")
@Expose
private Integer billNumber;
@SerializedName("LocationCode")
@Expose
private Integer locationCode;
@SerializedName("UserCode")
@Expose
private Integer userCode;
@SerializedName("MemberId")
@Expose
private String memberId;
@SerializedName("MemberType")
@Expose
private String memberType;
@SerializedName("OpeningBalance")
@Expose
private Double openingBalance;
@SerializedName("TableNumber")
@Expose
private int tableNumber;
@SerializedName("PAX")
@Expose
private Integer pAX;
@SerializedName("CouponNumber")
@Expose
private Integer couponNumber;
@SerializedName("BillAmount")
@Expose
private Double billAmount;
@SerializedName("RoundOff")
@Expose
private Double roundOff;
@SerializedName("BillItems")
@Expose
private List<BillItem> billItems = null;


    public BillSaveApi(Integer billNumber, Integer locationCode, Integer userCode, String memberId, String memberType, Double openingBalance, int tableNumber, Integer pAX, Integer couponNumber, Double billAmount, Double roundOff, List<BillItem> billItems) {
        this.billNumber = billNumber;
        this.locationCode = locationCode;
        this.userCode = userCode;
        this.memberId = memberId;
        this.memberType = memberType;
        this.openingBalance = openingBalance;
        this.tableNumber = tableNumber;
        this.pAX = pAX;
        this.couponNumber = couponNumber;
        this.billAmount = billAmount;
        this.roundOff = roundOff;
        this.billItems = billItems;
    }

    public Integer getBillNumber() {
return billNumber;
}

public void setBillNumber(Integer billNumber) {
this.billNumber = billNumber;
}

public Integer getLocationCode() {
return locationCode;
}

public void setLocationCode(Integer locationCode) {
this.locationCode = locationCode;
}

public Integer getUserCode() {
return userCode;
}

public void setUserCode(Integer userCode) {
this.userCode = userCode;
}

public String getMemberId() {
return memberId;
}

public void setMemberId(String memberId) {
this.memberId = memberId;
}

public String getMemberType() {
return memberType;
}

public void setMemberType(String memberType) {
this.memberType = memberType;
}

public Double getOpeningBalance() {
return openingBalance;
}

public void setOpeningBalance(Double openingBalance) {
this.openingBalance = openingBalance;
}

public int getTableNumber() {
return tableNumber;
}

public void setTableNumber(int tableNumber) {
this.tableNumber = tableNumber;
}

public Integer getPAX() {
return pAX;
}

public void setPAX(Integer pAX) {
this.pAX = pAX;
}

public Integer getCouponNumber() {
return couponNumber;
}

public void setCouponNumber(Integer couponNumber) {
this.couponNumber = couponNumber;
}

public Double getBillAmount() {
return billAmount;
}

public void setBillAmount(Double billAmount) {
this.billAmount = billAmount;
}

public Double getRoundOff() {
return roundOff;
}

public void setRoundOff(Double roundOff) {
this.roundOff = roundOff;
}

public List<BillItem> getBillItems() {
return billItems;
}

public void setBillItems(List<BillItem> billItems) {
this.billItems = billItems;
}

}