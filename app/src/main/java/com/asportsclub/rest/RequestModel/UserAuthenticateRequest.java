package com.asportsclub.rest.RequestModel;

public class UserAuthenticateRequest {

    String UserName;
    String UserPassword;
    int VenderId;

    public UserAuthenticateRequest(String userName, String userPassword, int venderId) {
        UserName = userName;
        UserPassword = userPassword;
        VenderId = venderId;
    }
}
