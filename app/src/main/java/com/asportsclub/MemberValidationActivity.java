package com.asportsclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.asportsclub.utils.AppMessages;
import com.asportsclub.utils.StringUtils;

import java.util.Timer;
import java.util.TimerTask;

public class MemberValidationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_member_id;
    TextView txt_error;
    Button btn_validate;
    ImageView img_member;
    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_validation);
        edt_member_id = (EditText)findViewById(R.id.edt_member_id);
        txt_error = (TextView) findViewById(R.id.txt_error);
        btn_validate = (Button) findViewById(R.id.btn_validate);
        img_member = (ImageView)findViewById(R.id.img_member);
        btn_validate.setOnClickListener(this);
        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");


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
        String member_id = edt_member_id.getText().toString();
        if (StringUtils.isEmpty(member_id)) {
            showTextView(txt_error, AppMessages.CommonSignInSignUpMessages.NO_MEMBERSHIP_ID);
            return;
        }

        Intent i = new Intent(this, TableBookingActivity.class);
        i.putExtra("member_id", member_id);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();

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
