package com.asportsclub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.asportsclub.rest.Response.MembershipDetail;
import com.asportsclub.rest.Response.VenderTableDetail;
import com.asportsclub.rest.Response.VenderTableDetails;
import com.asportsclub.rest.RestCallBack;
import com.asportsclub.rest.RestServiceFactory;
import com.asportsclub.utils.AppMessages;
import com.asportsclub.utils.StringUtils;
import com.asportsclub.utils.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Response;

public class MemberValidationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_member_id;
    TextView txt_error;
    TextView btn_validate;
    ProgressBar progressBar;
    private int tableId,selctedVenderId;
    private Context context;
    private VenderTableDetail mTableDetail;
    private String selectvenderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_member_validation);
        context=this;
        edt_member_id = (EditText) findViewById(R.id.edt_member_id);
        txt_error = (TextView) findViewById(R.id.txt_error);
        btn_validate = (TextView) findViewById(R.id.btn_validate);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        btn_validate.setOnClickListener(this);
        RefrenceWrapper.getRefrenceWrapper(this).getFontTypeFace().setRobotoMediumTypeFace(this,findViewById(R.id.text_signin));
        RefrenceWrapper.getRefrenceWrapper(this).getFontTypeFace().setRobotoRegularTypeFace(this,edt_member_id,btn_validate);
        tableId = getIntent().getIntExtra("tableId", 0);
        selctedVenderId = getIntent().getIntExtra("selctedVenderId",0);
        mTableDetail = (VenderTableDetail) getIntent().getSerializableExtra("tableDetail");
        selectvenderName=getIntent().getStringExtra("vendername");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_validate:
                doValidation();
                break;

        }
    }

    private void doValidation() {
        String member_id = edt_member_id.getText().toString();
        if (StringUtils.isEmpty(member_id)) {
            showTextView(txt_error, AppMessages.CommonSignInSignUpMessages.NO_MEMBERSHIP_ID);
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
            hitApiToValidateMemberShip();


    }

    private void hitApiToValidateMemberShip() {

        Call<MembershipDetail> commentsCall = RestServiceFactory.createService().getMembershipValidation("T-0171"
        );

        commentsCall.enqueue(new RestCallBack<MembershipDetail>() {
            @Override
            public void onFailure(Call<MembershipDetail> call, String message) {
                ToastUtils.show(context,message);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(Call<MembershipDetail> call, Response<MembershipDetail> restResponse, MembershipDetail response) {
                if(response.getStatusCode().getErrorCode()==0){
                    progressBar.setVisibility(View.GONE);
                    ToastUtils.show(context,response.getMembershipDetails().getMemberName());
                    Intent i = new Intent(context, ItemActivity.class);
                    i.putExtra("memberDetail", response.getMembershipDetails());
                    i.putExtra("tableId",tableId);
                    i.putExtra("vendername",selectvenderName);
                    i.putExtra("selctedVenderId",selctedVenderId);
                    i.putExtra("tableDetail",mTableDetail);


                    startActivity(i);
                    finish();
                }
                else{
                    ToastUtils.show(context,response.getStatusCode().getErrorMessage());
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
