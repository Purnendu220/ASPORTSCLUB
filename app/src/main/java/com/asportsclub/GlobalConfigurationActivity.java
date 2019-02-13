package com.asportsclub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.asportsclub.adapter.GlobalConfigAdapter;
import com.asportsclub.rest.Response.GlobalVenderDetail;
import com.asportsclub.rest.Response.GlobalVenderDetails;
import com.asportsclub.rest.RestCallBack;
import com.asportsclub.rest.RestServiceFactory;
import com.asportsclub.utils.AppMessages;
import com.asportsclub.utils.AppSharedPreferences;
import com.asportsclub.utils.StringUtils;
import com.asportsclub.utils.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Response;

public class GlobalConfigurationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_member_id;
    TextView txt_error;
    TextView btn_validate;
    ProgressBar progressBar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_setting_globalurl);
        context=this;
        edt_member_id = (EditText)findViewById(R.id.edt_member_id);
        txt_error = (TextView) findViewById(R.id.txt_error);
        btn_validate = (TextView) findViewById(R.id.btn_validate);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        btn_validate.setOnClickListener(this);

        RefrenceWrapper.getRefrenceWrapper(this).getFontTypeFace().setRobotoMediumTypeFace(this,findViewById(R.id.text_signin));
        RefrenceWrapper.getRefrenceWrapper(this).getFontTypeFace().setRobotoRegularTypeFace(this,edt_member_id,btn_validate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_validate:
                doValidation();
                break;

        }
    }

    private void doValidation(){
        String url = edt_member_id.getText().toString();
       boolean match= Patterns.WEB_URL.matcher(url).matches();
        if (StringUtils.isEmpty(url)||!match) {
            ToastUtils.show(context,AppMessages.CommonSignInSignUpMessages.NO_MEMBERSHIP_ID);
            return;
        }
        AppSharedPreferences.getInstance().setUrl(url);
        progressBar.setVisibility(View.VISIBLE);
        hitApitogetGlobalConfiguration(url);


    }

    private void hitApitogetGlobalConfiguration(final String url) {


        Call<GlobalVenderDetails> commentsCall = RestServiceFactory.createService().getGlobalConfiguration(
        );
        commentsCall.enqueue(new RestCallBack<GlobalVenderDetails>() {
            @Override
            public void onFailure(Call<GlobalVenderDetails> call, String message) {
                ToastUtils.show(context, message);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(Call<GlobalVenderDetails> call, Response<GlobalVenderDetails> restResponse, GlobalVenderDetails response) {
                progressBar.setVisibility(View.GONE);
                if (response.getStatusCode().getErrorCode() == 0) {
                    Intent i = new Intent(context, LoginActivity.class);
                    i.putExtra("globalvenderData",response);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();

                } else {
                    ToastUtils.show(context, response.getStatusCode().getErrorMessage());
                }
            }
        });
    }
    public void showTextView(final TextView view, String message) {
        view.setVisibility(View.VISIBLE);
        view.setText(message);
        Timer t = new Timer(false);
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        view.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }, 3000);
    }
}
