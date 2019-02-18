package com.asportsclub.rest;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.asportsclub.R;
import com.asportsclub.rest.Response.ResponseModel;
import com.asportsclub.utils.AppConstants;
import com.asportsclub.utils.AppContext;
import com.asportsclub.utils.NetworkUtil;
import com.asportsclub.utils.ToastUtils;

import com.google.gson.Gson;




import java.io.IOException;

import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MyU10 on 1/21/2017.
 */

public abstract class RestCallBack<T> implements Callback<T> {

    public abstract void onFailure(Call<T> call, String message);

    public abstract void onResponse(Call<T> call, Response<T> restResponse, T response);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (call.isCanceled()) {
            onFailure(call, "");
            return;
        }

        if (NetworkUtil.isInternetAvailable) {
            if (t.getLocalizedMessage() != null)
                onFailure(call, t.getLocalizedMessage());
            else
                onFailure(call, "");
        } else
            onFailure(call, AppContext.getInstance().getContext().getString(R.string.something_went_wrong));

        try {
            ToastUtils.showErrorOnLive(AppContext.getInstance().getContext(), t.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int index = call.request().url().toString().indexOf("?");
        if (index == -1)
            index = call.request().url().toString().length();



    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
                onResponse(call, response, response.body());
            }
            onResponse(call, response, response.body());

        }
    }

    public static boolean isSuccessFull(ResponseModel responseModel) {
        if (responseModel.statusCode.equals(AppConstants.ApiParamValue.SUCCESS_RESPONSE_CODE))
            return true;
        else
            return false;
    }

    private String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "response can't be converted into a string";
        }
    }
}
