package com.asportsclub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.asportsclub.utils.AppSharedPreferences;

import androidx.annotation.Nullable;


public class SplashActivity extends Activity {
private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        context=this;
        CountDownTimer timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                if(AppSharedPreferences.getInstance().getURL().isEmpty()){
                    Intent in=new Intent(context,GlobalConfigurationActivity.class);
                    startActivity(in);
                }else if(AppSharedPreferences.getInstance().getUserName().isEmpty()){
                    Intent in=new Intent(context,LoginActivity.class);
                    startActivity(in);
                }else{
                    Intent in=new Intent(context,TableBookingActivity.class);
                    in.putExtra("vernderDetail", AppSharedPreferences.getInstance().getTableInfo());
                    startActivity(in);
                }
                finish();
            }
        }.start();

    }
}
