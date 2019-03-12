package com.asportsclub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.asportsclub.adapter.OpenChecksAdapter;
import com.asportsclub.adapter.SelectedItemAdapter;
import com.asportsclub.rest.Response.AuthenticateUserResponse;
import com.asportsclub.rest.Response.Item;
import com.asportsclub.rest.Response.ItemHeaderModel;
import com.asportsclub.rest.Response.OpenBillDetail;
import com.asportsclub.rest.Response.OpenBillDetails;
import com.asportsclub.rest.Response.OpenChecksHeaderModel;
import com.asportsclub.rest.RestServiceFactory;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.utils.AppSharedPreferences;
import com.asportsclub.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class OpenChecksActivity extends AppCompatActivity implements AdapterCallbacks<OpenBillDetail> {

    private RecyclerView itemOpenChecksView;
    private Context context;
    private OpenBillDetails openCheckDetail;
    private OpenChecksAdapter mOpenCheckAdapter;
    List<OpenBillDetail> mOpenBillDetailList = new ArrayList<>();
    private ImageView imageViewLogout,imageViewSetting;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_allopenchecks);
        context=this;
        itemOpenChecksView=(RecyclerView)findViewById(R.id.itemOpenChecksView);
        imageViewSetting=(ImageView)findViewById(R.id.imageviewSetting);
        imageViewLogout=(ImageView)findViewById(R.id.imageviewLogout);
        openCheckDetail = (OpenBillDetails) getIntent().getSerializableExtra("openCheckDetails");
        mOpenBillDetailList.addAll(openCheckDetail.getOpenBillDetails());
        mOpenCheckAdapter = new OpenChecksAdapter(context,false,this);
        itemOpenChecksView.setLayoutManager(new LinearLayoutManager(context));
        itemOpenChecksView.setHasFixedSize(false);
        itemOpenChecksView.setAdapter(mOpenCheckAdapter);
        itemOpenChecksView.setItemAnimator(new DefaultItemAnimator());
        mOpenCheckAdapter.addHeader(new OpenChecksHeaderModel("Table Name","Bill No.","MembershipId","Member Name","Bill Amount"));
        mOpenCheckAdapter.addAllItem(mOpenBillDetailList);
        mOpenCheckAdapter.notifyDataSetChanged();
        imageViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showBasicwithTwoButton(context, "Edit URL","Are you sure want to change app configuration URL.", "Yes", "Cancel",new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        AppSharedPreferences.getInstance().setUrl("");
                        AppSharedPreferences.getInstance().setuserName("");
                        RestServiceFactory.apiService=null;
                        AppSharedPreferences.getInstance().setTableInfo(null);
                        Intent i = new Intent(context, GlobalConfigurationActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                });

            }
        });
        imageViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showBasicwithTwoButton(context, "Log Out","Are you sure want to logout ??", "Yes", "No",new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        AppSharedPreferences.getInstance().setuserName("");
                        AppSharedPreferences.getInstance().setTableInfo(null);
                        Intent i = new Intent(context, LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });
    }

    @Override
    public void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, OpenBillDetail model, int position) {

    }

    @Override
    public void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, OpenBillDetail model, int position) {

    }

    @Override
    public void onShowLastItem() {

    }
}
