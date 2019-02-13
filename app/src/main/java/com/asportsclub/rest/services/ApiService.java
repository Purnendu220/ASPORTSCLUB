package com.asportsclub.rest.services;

import com.asportsclub.rest.Response.GlobalVenderDetail;
import com.asportsclub.rest.Response.GlobalVenderDetails;
import com.asportsclub.rest.Response.MenuItems;
import com.asportsclub.rest.Response.ResponseModel;
import com.google.gson.JsonObject;


import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    @Headers("Content-type: application/json")
    @GET(ServiceConstants.ITEM_LIST)
    Call<MenuItems> getItemList(@Query(ServiceConstants.VENDER_ID) int venderId);

}
