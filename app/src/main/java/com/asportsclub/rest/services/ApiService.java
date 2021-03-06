package com.asportsclub.rest.services;

import com.asportsclub.rest.RequestModel.BillSaveApi;
import com.asportsclub.rest.Response.AuthenticateUserResponse;
import com.asportsclub.rest.Response.GlobalVenderDetail;
import com.asportsclub.rest.Response.GlobalVenderDetails;
import com.asportsclub.rest.Response.ItemBillDetail;
import com.asportsclub.rest.Response.MembershipDetail;
import com.asportsclub.rest.Response.MenuItems;
import com.asportsclub.rest.Response.OpenBillDetails;
import com.asportsclub.rest.Response.ResponseModel;
import com.asportsclub.rest.Response.SaveBillResponse;
import com.asportsclub.rest.Response.VenderTableDetail;
import com.asportsclub.rest.Response.VenderTableDetails;
import com.google.gson.JsonObject;


import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Narendra on 10/20/2016.
 */
public interface ApiService {

    @Headers("Content-type: application/json")
    @GET(ServiceConstants.GLOBAL_CONFIGURATION)
    Call<GlobalVenderDetails> getGlobalConfiguration();

    @FormUrlEncoded
    @POST(ServiceConstants.AUTHENTICATEUSER)
    Call<AuthenticateUserResponse> getAuthenticateUser(@Field("UserName") String username,
                                                       @Field("UserPassword") String userpassword,
                                                       @Field("VenderId") int venderID);
    @Headers("Content-type: application/json")
    @GET(ServiceConstants.TABLEDATAFROMVENDERID)
    Call<VenderTableDetails> getTableDataFromvenderId(@Query("MainLocationCode") int venderId,
                                                      @Query("CurrentLocationCode") int currentvenderId);

    @Headers("Content-type: application/json")
    @GET(ServiceConstants.MEMBERSHIPVALIDATION)
    Call<MembershipDetail> getMembershipValidation(@Query("MembershipId") String membershipId);

    @Headers("Content-type: application/json")
    @GET(ServiceConstants.ITEM_LIST)
    Call<MenuItems> getItemList(@Query(ServiceConstants.VENDER_ID) int venderId);

    @Headers("Content-type: application/json")
    @POST(ServiceConstants.SAVE_BILL)
    Call<SaveBillResponse> saveBill(@Body BillSaveApi requestBillSave);

    @FormUrlEncoded
    @POST(ServiceConstants.BILLDETAILS)
    Call<ItemBillDetail> getBillDetail(@Field("BillNumber") int billnumber,
                                       @Field("LocationCode") int locationcode
                                                      );

    @Headers("Content-type: application/json")
    @GET(ServiceConstants.GETALLOPENCHECKS)
    Call<OpenBillDetails> getAllOpenChecks(@Query("MainLocationCode") int venderId,
                                           @Query("CurrentLocationCode") int currentvenderId);

}
