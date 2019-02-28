
package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MembershipDetails implements Serializable {

    @SerializedName("MembershipId")
    @Expose
    private String membershipId;
    @SerializedName("MemberName")
    @Expose
    private String memberName;

    @SerializedName("MemberType")
    @Expose
    private String MemberType;
    @SerializedName("CouponNumber")
    @Expose
    private Integer CouponNumber;
    @SerializedName("OpeningBalance")
    @Expose
    private Double OpeningBalance;


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

    public String getMemberType() {
        return MemberType;
    }

    public void setMemberType(String memberType) {
        MemberType = memberType;
    }

    public Integer getCouponNumber() {
        return CouponNumber;
    }

    public void setCouponNumber(Integer couponNumber) {
        CouponNumber = couponNumber;
    }

    public Double getOpeningBalance() {
        return OpeningBalance;
    }

    public void setOpeningBalance(Double openingBalance) {
        OpeningBalance = openingBalance;
    }
}
