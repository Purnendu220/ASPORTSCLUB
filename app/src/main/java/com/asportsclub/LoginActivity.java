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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.asportsclub.utils.AppMessages;
import com.asportsclub.utils.StringUtils;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edt_email,edt_password;
    Button btn_sign_in;
    TextView txt_error;
    MaterialSpinner spinner;
    ArrayList<VenderDetailsModel> mListVendorDetails = new ArrayList<>();
    String mSelectedVendor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = (EditText)findViewById(R.id.edt_email);
        edt_password = (EditText)findViewById(R.id.edt_password);
        txt_error = (TextView) findViewById(R.id.txt_error);
        btn_sign_in = (Button) findViewById(R.id.btn_sign_in);
        spinner = (MaterialSpinner) findViewById(R.id.spinner);
//        mListVendorDetails.add(new VenderDetailsModel(0,"SELECT VENDORP"));
//        mListVendorDetails.add(new VenderDetailsModel(8,"COFFEE SHOP"));
//        mListVendorDetails.add(new VenderDetailsModel(8,"GARDEN VIEW BAR"));
//        mListVendorDetails.add(new VenderDetailsModel(8,"LOUNGE BAR"));
//        mListVendorDetails.add(new VenderDetailsModel(8,"RESTAURANT"));
        // spinner.setItems(mListVendorDetails);


        spinner.setItems("SELECT VENDOR", "COFFEE SHOP", "GARDEN VIEW BAR", "LOUNGE BAR", "RESTAURANT");

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                if(position>0){
                    mSelectedVendor = item;
                }
                else{
                    mSelectedVendor = null;
                }
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        btn_sign_in.setOnClickListener(this);
        call_ime_action();


    }

    private void call_ime_action() {
        edt_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    doSignIn();
                }
                if(actionId == EditorInfo.IME_ACTION_NEXT){
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
        if(spinner.getSelectedIndex()==0){
            showTextView(txt_error, AppMessages.CommonSignInSignUpMessages.SELECT_VENDOR_NAME);
            return;
        }
        Intent i = new Intent(this, TableBookingActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("username", email);
        i.putExtra("password", password);
        i.putExtra("vendor_detail",mSelectedVendor);
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
