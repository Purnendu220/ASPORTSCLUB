
package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MembershipDetail implements Serializable {

    @SerializedName("StatusCode")
    @Expose
    private StatusCode statusCode;
    @SerializedName("MembershipDetails")
    @Expose
    private MembershipDetails membershipDetails;

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public MembershipDetails getMembershipDetails() {
        return membershipDetails;
    }

    public void setMembershipDetails(MembershipDetails membershipDetails) {
        this.membershipDetails = membershipDetails;
    }

}
