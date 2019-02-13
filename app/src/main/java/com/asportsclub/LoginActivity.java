package com.asportsclub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.asportsclub.adapter.GlobalConfigAdapter;
import com.asportsclub.rest.RequestModel.UserAuthenticateRequest;
import com.asportsclub.rest.Response.AuthenticateUserResponse;
import com.asportsclub.rest.Response.GlobalVenderDetail;
import com.asportsclub.rest.Response.GlobalVenderDetails;
import com.asportsclub.rest.Response.ResponseModel;
import com.asportsclub.rest.RestCallBack;
import com.asportsclub.rest.RestServiceFactory;
import com.asportsclub.utils.AppContext;
import com.asportsclub.utils.AppMessages;
import com.asportsclub.utils.AppSharedPreferences;
import com.asportsclub.utils.DialogUtils;
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
    private int mSelectVenderId;
    private ProgressBar progressBar;
    private Context context;
    ImageView imageViewSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        AppContext.getInstance().setContext(getApplication());
        context = this;
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_password = (EditText) findViewById(R.id.edt_password);
        txt_error = (TextView) findViewById(R.id.txt_error);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        spinner = (Spinner) findViewById(R.id.spinner);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        imageViewSetting = (ImageView) findViewById(R.id.imageviewSetting);
        RefrenceWrapper.getRefrenceWrapper(this).getFontTypeFace().setRobotoMediumTypeFace(this, findViewById(R.id.text_signin));
        RefrenceWrapper.getRefrenceWrapper(this).getFontTypeFace().setRobotoRegularTypeFace(this, edt_email, btn_sign_in);
        if(getIntent().hasExtra("globalvenderData")){
            GlobalVenderDetails globalVenderDetails=  (GlobalVenderDetails) getIntent().getSerializableExtra("globalvenderData");
            for (int i = 0; i < globalVenderDetails.getGlobalVenderDetails().size(); i++) {
                        mListVendorDetails.add(new GlobalVenderDetail(globalVenderDetails.getGlobalVenderDetails().get(i).getVenderId(), globalVenderDetails.getGlobalVenderDetails().get(i).getVenderName()));
                    }

                    adapter = new GlobalConfigAdapter(context, R.layout.spinner_item, mListVendorDetails);
                    spinner.setAdapter(adapter);
        }else {
            progressBar.setVisibility(View.VISIBLE);
            hitApitogetGlobalConfiguration();
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                GlobalVenderDetail model = (GlobalVenderDetail) adapter.getItem(position);
                if (model != null) {
                    Toast.makeText(context, model.getVenderName() + "", Toast.LENGTH_LONG);
                    mSelectedVendor = model.getVenderName();
                    mSelectVenderId = model.getVenderId();
                } else {
                    mSelectedVendor = null;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_sign_in.setOnClickListener(this);
        imageViewSetting.setOnClickListener(this);
        call_ime_action();


    }

    private void hitApitogetGlobalConfiguration() {


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
                    for (int i = 0; i < response.getGlobalVenderDetails().size(); i++) {
                        mListVendorDetails.add(new GlobalVenderDetail(response.getGlobalVenderDetails().get(i).getVenderId(), response.getGlobalVenderDetails().get(i).getVenderName()));
                    }

                    adapter = new GlobalConfigAdapter(context, R.layout.spinner_item, mListVendorDetails);
                    spinner.setAdapter(adapter);
                } else {
                    ToastUtils.show(context, response.getStatusCode().getErrorMessage());
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

            case R.id.imageviewSetting:
                DialogUtils.showBasicwithTwoButton(context, "Edit URL", "Are you sure want to change app configuration URL.", "Yes", "Cancel", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        AppSharedPreferences.getInstance().setUrl("");
                        RestServiceFactory.apiService=null;
                        AppSharedPreferences.getInstance().setuserName("");
                        AppSharedPreferences.getInstance().setTableInfo(null);
                        Intent i = new Intent(context, GlobalConfigurationActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                });
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
//        if (!StringUtils.isEmailValid(email)) {
//            showTextView(txt_error, AppMessages.CommonSignInSignUpMessages.INVALID_EMAIL);
//            return;
//        }
//        if (!StringUtils.isPasswordLengthValid(password, 6)) {
//            showTextView(txt_error, AppMessages.CommonSignInSignUpMessages.MIN_PASSWORD_CHECK);
//            return;
//        }
        if (mSelectedVendor == null) {
            showTextView(txt_error, AppMessages.CommonSignInSignUpMessages.SELECT_VENDOR_NAME);
            return;
        }

        hitApiToAuthenticateUser(email, password, mSelectVenderId);


    }

    private void hitApiToAuthenticateUser(String email, String password, int mSelectVenderId) {

        UserAuthenticateRequest userAuthenticateRequest = new UserAuthenticateRequest(email, password, mSelectVenderId);
        Call<AuthenticateUserResponse> userResponseCall = RestServiceFactory.createService().getAuthenticateUser(email, password, mSelectVenderId
        );
        userResponseCall.enqueue(new RestCallBack<AuthenticateUserResponse>() {
            @Override
            public void onFailure(Call<AuthenticateUserResponse> call, String message) {
                ToastUtils.show(context, message);
            }

            @Override
            public void onResponse(Call<AuthenticateUserResponse> call, Response<AuthenticateUserResponse> restResponse, AuthenticateUserResponse response) {
                if (response.getStatusCode().getErrorCode() == 0) {
                    if (response.getVenderTableDetails().size() > 0) {
                        AppSharedPreferences.getInstance().setuserName(response.getUserDetail().getUserName());
                        AppSharedPreferences.getInstance().setTableInfo(response);
                        Intent i = new Intent(context, TableBookingActivity.class);
                        i.putExtra("vernderDetail", response);
                        startActivity(i);
                        finish();
                    }else{
                        ToastUtils.show(context, "No Table here.");
                    }
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
