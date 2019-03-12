package com.asportsclub.rest.Response;


public class OpenChecksHeaderModel {

    String TableName;
    String BillNumber;
    String MembershipId;
    String MemberName;
    String BillAmount;

    public OpenChecksHeaderModel(String TableName, String BillNumber, String MembershipId, String MemberName, String BillAmount) {
        this.TableName = TableName;
        this.BillNumber = BillNumber;
        this.MembershipId = MembershipId;
        this.MemberName = MemberName;
        this.BillAmount = BillAmount;
    }


    public String getTableName() {
        return TableName;
    }

    public void setTableName(String tableName) {
        TableName = tableName;
    }

    public String getBillNumber() {
        return BillNumber;
    }

    public void setBillNumber(String billNumber) {
        BillNumber = billNumber;
    }

    public String getMembershipId() {
        return MembershipId;
    }

    public void setMembershipId(String membershipId) {
        MembershipId = membershipId;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getBillAmount() {
        return BillAmount;
    }

    public void setBillAmount(String billAmount) {
        BillAmount = billAmount;
    }
}
