package com.asportsclub;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.asportsclub.utils.AppMessages;
import com.asportsclub.utils.StringUtils;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_email,edt_password;
    Button btn_sign_in;
    TextView txt_error;
    ImageView img_email,img_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edt_email = (EditText)findViewById(R.id.edt_email);
        edt_password = (EditText)findViewById(R.id.edt_password);
        txt_error = (TextView) findViewById(R.id.txt_error);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        img_email = (ImageView)findViewById(R.id.img_email);
        img_password = (ImageView)findViewById(R.id.img_password);
        btn_sign_in.setOnClickListener(this);
        setTextChangeListener(edt_email);
        setTextChangeListener(edt_password);
        setFocusChangeListener(edt_email);
        setFocusChangeListener(edt_password);


    }
    private void setTextChangeListener(final EditText editText) {

        switch (editText.getId()) {
            case R.id.edt_email:
                addTextChangeListener(img_email, edt_email, R.drawable.new_post, R.drawable.new_post_filled, 0);
                break;
            case R.id.edt_password:
                call_ime_action();
                addTextChangeListener(img_password, edt_password, R.drawable.lock, R.drawable.lock_filled, 6);
                break;
        }
    }

    private void addTextChangeListener(final ImageView mImageView, final EditText mEditText, final int drawable1, final int drawable3, final int mLength) {

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = mEditText.getText().length();
                int color = 0;
//                if (length == 0) {
//                    color = ContextCompat.getColor(getActivity(), R.color.icon_color);
//                } else
                if ((length < mLength) || (mLength == 0 && (!StringUtils.isEmailValid(mEditText.getText().toString())))) {
                    color = ContextCompat.getColor(LoginActivity.this, R.color.red);
                } else {
                    mImageView.setImageResource(drawable3);
                    mImageView.setColorFilter(0);
                }
                if (color != 0) {
                    mImageView.setImageResource(drawable1);
                    mImageView.setColorFilter(color);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void setFocusChangeListener(final EditText editText) {

        switch (editText.getId()) {
            case R.id.edt_email:
                addFocusChangeListener(img_email, edt_email, R.drawable.new_post, R.drawable.new_post_filled, 0);
                break;
            case R.id.edt_password:
                addFocusChangeListener(img_password, edt_password, R.drawable.lock, R.drawable.lock_filled, 6);
                break;
        }
    }
    private void addFocusChangeListener(final ImageView mImageView, final EditText mEditText, final int drawable1, final int drawable3, final int mLength) {
        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(mEditText.getText().length() == 0) {
                    if (hasFocus) {
                        mImageView.setColorFilter(ContextCompat.getColor(LoginActivity.this, R.color.red));
                    } else {
                        mImageView.setColorFilter(ContextCompat.getColor(LoginActivity.this, R.color.icon_color));
                    }
                }
            }
        });
    }
    private void call_ime_action() {
        edt_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    doSignIn();
                }
                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
                doSignIn();
                break;

        }
    }

    private void doSignIn() {
        String email = edt_email.getText().toString();
        String password = edt_password.getText().toString();

        if (StringUtils.isEmpty(email)) {
            showTextView(txt_error, AppMessages.CommonSignInSignUpMessages.NO_EMAIL);
            return;
        }
        if (StringUtils.isEmpty(password)) {
            showTextView(txt_error, AppMessages.CommonSignInSignUpMessages.NO_PASSWORD);
            return;
        }
        if (!StringUtils.isEmailValid(email)) {
            showTextView(txt_error, AppMessages.CommonSignInSignUpMessages.INVALID_EMAIL);
            return;
        }
        if (!StringUtils.isPasswordLengthValid(password, 6)) {
            showTextView(txt_error, AppMessages.CommonSignInSignUpMessages.MIN_PASSWORD_CHECK);
            return;
        }
        Intent i = new Intent(this, MemberValidationActivity.class);
        i.putExtra("username", email);
        i.putExtra("password", password);
        startActivity(i);



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
