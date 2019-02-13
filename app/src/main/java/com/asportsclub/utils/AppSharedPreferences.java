package com.asportsclub.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.asportsclub.rest.Response.AuthenticateUserResponse;


import static com.asportsclub.utils.AppConstants.PREF_USERINFO;


public class AppSharedPreferences {

    private static AppSharedPreferences instance;


    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String VENDORID="vendorid";


    //From v2...
    private static final String PREF_NAME_USER_INFO = "userinfo";


    private String TAG = this.getClass().getSimpleName();
    private SharedPreferences mPrefs;
    private Editor mPrefsEditor;


    private Context context;




    public AppSharedPreferences(Context context) {
        this.context = context;
        this.mPrefs = context.getSharedPreferences(PREF_NAME_USER_INFO, Context.MODE_PRIVATE);
        this.mPrefsEditor = mPrefs.edit();
    }

    public static AppSharedPreferences getInstance() {

        if (instance == null) {
            instance = new AppSharedPreferences(AppContext.getInstance().getContext());
        }
        return instance;
    }

    public static AppSharedPreferences getInstance(Context context) {

        instance = new AppSharedPreferences(context);
        return instance;
    }


    public String getURL() {
        return mPrefs.getString(URL, "");
    }

    public void setUrl(String value) {
        mPrefsEditor.putString(URL, value);
        mPrefsEditor.commit();
    }

    public String getUserName() {
        return mPrefs.getString(USERNAME, "");
    }

    public void setuserName(String value) {
        mPrefsEditor.putString(USERNAME, value);
        mPrefsEditor.commit();
    }

    public String getvendorID() {
        try {
            return mPrefs.getString(VENDORID, "0");
        } catch (Exception e) {
            e.printStackTrace();
            return String.valueOf(mPrefs.getInt(VENDORID, 0));
        }
    }

    public void setVendorid(int vendorId) {
        mPrefsEditor.putString(VENDORID, String.valueOf(vendorId));
        mPrefsEditor.commit();
    }

    public AuthenticateUserResponse getTableInfo() {
        AuthenticateUserResponse mUserModel = null;
        String details = mPrefs.getString(PREF_USERINFO, "");
        if (details.length() != 0)
            mUserModel = JsonUtils.fromJson(details, AuthenticateUserResponse.class);

        return mUserModel;
    }

    public void setTableInfo(AuthenticateUserResponse details) {
        String value = "";
        if (details != null)
            value = JsonUtils.toJson(details);
        mPrefsEditor.putString(PREF_USERINFO, value);
        mPrefsEditor.commit();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////// NEW CHAT API LOGIC /////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////



    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

}
