package com.asportsclub;


import android.app.Application;
import android.content.Context;



import com.asportsclub.utils.AppContext;


public class SportApplication extends Application  {

    private Context context;
    @Override
    public void onCreate() {

        super.onCreate();
        context = this;
        AppContext.getInstance().setContext(this);
    }


}
