package com.asportsclub;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.asportsclub.adapter.GlobalConfigAdapter;
import com.asportsclub.rest.Response.GlobalVenderDetail;
import com.asportsclub.rest.Response.GlobalVenderDetails;
import com.asportsclub.rest.Response.ResponseModel;
import com.asportsclub.rest.RestCallBack;
import com.asportsclub.rest.RestServiceFactory;
import com.asportsclub.utils.AppContext;
import com.asportsclub.utils.AppMessages;
import com.asportsclub.utils.StringUtils;
import com.asportsclub.utils.ToastUtils;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_email, edt_password;
    Button btn_sign_in;
    TextView txt_error;
    Spinner spinner;
    ArrayList<GlobalVenderDetail> mListVendorDetails = new ArrayList<>();
    String mSelectedVendor;
    GlobalConfigAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppContext.getInstance().setContext(getApplication());

        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        txt_error = (TextView) findViewById(R.id.txt_error);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        spinner = (Spinner) findViewById(R.id.spinner);

        hitApitogetGlobalConfiguration();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalVenderDetail model = adapter.getItem(position);
                if(model!=null){
                    Toast.makeText(LoginActivity.this,model.getVenderName()+"",Toast.LENGTH_LONG);
                    mSelectedVendor = model.getVenderName();
                }else{
                    mSelectedVendor = null;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_sign_in.setOnClickListener(this);
        call_ime_action();


    }

    private void hitApitogetGlobalConfiguration() {


        Call<GlobalVenderDetails> commentsCall = RestServiceFactory.createService().getGlobalConfiguration(
        );
        commentsCall.enqueue(new RestCallBack<GlobalVenderDetails>() {
            @Override
            public void onFailure(Call<GlobalVenderDetails> call, String message) {
                ToastUtils.show(LoginActivity.this,message);
            }

            @Override
            public void onResponse(Call<GlobalVenderDetails> call, Response<GlobalVenderDetails> restResponse, GlobalVenderDetails response) {
                if(response.getGlobalVenderDetails()!=null&& response.getGlobalVenderDetails().size()>0){
                    for(int i=0;i<response.getGlobalVenderDetails().size();i++){
                        mListVendorDetails.add(new GlobalVenderDetail(response.getGlobalVenderDetails().get(i).getVenderId(),response.getGlobalVenderDetails().get(i).getVenderName()));
                    }

                    adapter=new GlobalConfigAdapter(LoginActivity.this,R.layout.spinner_item,mListVendorDetails);
                    spinner.setAdapter(adapter);
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
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    hideKeyboard(LoginActivity.this);
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
        if (mSelectedVendor!=null) {
            showTextView(txt_error, AppMessages.CommonSignInSignUpMessages.SELECT_VENDOR_NAME);
            return;
        }
        Intent i = new Intent(this, TableBookingActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("username", email);
        i.putExtra("password", password);
        i.putExtra("vendor_detail", mSelectedVendor);
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

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
