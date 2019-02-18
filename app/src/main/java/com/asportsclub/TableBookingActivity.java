package com.asportsclub;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.asportsclub.adapter.GlobalConfigAdapter;
import com.asportsclub.adapter.TableBookingAdapter;
import com.asportsclub.rest.Response.AuthenticateUserResponse;
import com.asportsclub.rest.Response.GlobalVenderDetail;
import com.asportsclub.rest.Response.GlobalVenderDetails;
import com.asportsclub.rest.Response.UserValidVenderDetail;
import com.asportsclub.rest.Response.VenderTableDetail;
import com.asportsclub.rest.Response.VenderTableDetails;
import com.asportsclub.rest.RestCallBack;
import com.asportsclub.rest.RestServiceFactory;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.utils.AppSharedPreferences;
import com.asportsclub.utils.DialogUtils;
import com.asportsclub.utils.GridAutofitLayoutManager;
import com.asportsclub.utils.ToastUtils;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class TableBookingActivity extends AppCompatActivity implements AdapterCallbacks<VenderTableDetail> {

    RecyclerView recycler_table_view;
    TableBookingAdapter tableBookingAdapter;
    ArrayList<VenderTableDetail> list = new ArrayList<>();
    ArrayList<UserValidVenderDetail> mListVendorDetails = new ArrayList<>();
    Spinner filterSpinner2;
    MaterialSpinner filterSpinner1;
    int selectedSeat = -1;
    private AuthenticateUserResponse authenticateUserResponse;
    private GlobalConfigAdapter adapter;
    private ProgressBar progressBar;
    private Context context;
    private ImageView imageViewLogout,imageViewSetting;
    private int selctedVenderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_table_booking);
        context = this;
        authenticateUserResponse = (AuthenticateUserResponse) getIntent().getSerializableExtra("vernderDetail");
        selctedVenderId = authenticateUserResponse.getUserValidVenderDetails().get(0).getVenderId();

        recycler_table_view = (RecyclerView) findViewById(R.id.recycler_table_view);
        filterSpinner1 = (MaterialSpinner) findViewById(R.id.spinner_filter_one);
        filterSpinner2 = (Spinner) findViewById(R.id.spinner_filter_two);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        imageViewSetting=(ImageView)findViewById(R.id.imageviewSetting);
        imageViewLogout=(ImageView)findViewById(R.id.imageviewLogout);
        progressBar.setVisibility(View.GONE);

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
        recycler_table_view.setLayoutManager(new GridAutofitLayoutManager(TableBookingActivity.this, 120));
        recycler_table_view.setHasFixedSize(false);
        // recyclerViewClass.scrollToPosition(0);

        try {
            ((SimpleItemAnimator) recycler_table_view.getItemAnimator()).setSupportsChangeAnimations(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < authenticateUserResponse.getUserValidVenderDetails().size(); i++) {
            mListVendorDetails.add(new UserValidVenderDetail(authenticateUserResponse.getUserValidVenderDetails().get(i).getVenderId(), authenticateUserResponse.getUserValidVenderDetails().get(i).getVenderName()));
        }


        adapter = new GlobalConfigAdapter(TableBookingActivity.this, R.layout.spinner_item, mListVendorDetails);
        filterSpinner2.setAdapter(adapter);
        tableBookingAdapter = new TableBookingAdapter(TableBookingActivity.this, false, this);
        recycler_table_view.setAdapter(tableBookingAdapter);
        setTableData();
        filterSpinner1.setItems("View All", "View Occupied", "View Available");

        filterSpinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                switch (position) {
                    case 0:
                        setTableData();
                        break;
                    case 1:
                        viewOccupiedSeats();
                        break;
                    case 2:
                        viewAvailableSeats();
                        break;

                }

            }
        });
        filterSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserValidVenderDetail model = (UserValidVenderDetail) adapter.getItem(position);
                if (model != null) {
                    Toast.makeText(context, model.getVenderName() + "", Toast.LENGTH_LONG);
                    progressBar.setVisibility(View.VISIBLE);
                    hitApiTogetTableFromVenderId(model.getVenderId());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void hitApiTogetTableFromVenderId(final int venderId) {
        Call<VenderTableDetails> commentsCall = RestServiceFactory.createService().getTableDataFromvenderId(venderId
        );
        commentsCall.enqueue(new RestCallBack<VenderTableDetails>() {
            @Override
            public void onFailure(Call<VenderTableDetails> call, String message) {
                ToastUtils.show(context, message);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(Call<VenderTableDetails> call, Response<VenderTableDetails> restResponse, VenderTableDetails response) {
                if (response != null && response.getVenderTableDetails().size() > 0) {
                    progressBar.setVisibility(View.GONE);
                    authenticateUserResponse.setVenderTableDetails(response.getVenderTableDetails());
                    AppSharedPreferences.getInstance().setTableInfo(authenticateUserResponse);
                    setTableData();
                    selctedVenderId = venderId;

                }
                else{
                    ToastUtils.show(context,response.getStatusCode().getErrorMessage());
                }
            }
        });
    }

    private void setTableData() {
        list = new ArrayList<>();
        for (int i = 0; i < authenticateUserResponse.getVenderTableDetails().size(); i++) {
            if (authenticateUserResponse.getVenderTableDetails().get(i).getTableName().length() <= 3) {
                list.add(authenticateUserResponse.getVenderTableDetails().get(i));
            }
        }
//        list.addAll(authenticateUserResponse.getVenderTableDetails());

        tableBookingAdapter.clearAll();
        tableBookingAdapter.addAllTableData(list);
        tableBookingAdapter.notifyDataSetChanged();

    }

    private void viewAvailableSeats() {
        ArrayList<VenderTableDetail> available_seats = new ArrayList<>();

        for (VenderTableDetail model : list) {
            if (model.getTableStatus() == 0) {
                available_seats.add(model);
            }
        }
        tableBookingAdapter.clearAll();
        tableBookingAdapter.addAllTableData(available_seats);
        tableBookingAdapter.notifyDataSetChanged();


    }

    private void viewOccupiedSeats() {
        ArrayList<VenderTableDetail> occupied_seats = new ArrayList<>();

        for (VenderTableDetail model : list) {
            if (model.getTableStatus() == 1) {
                occupied_seats.add(model);
            }
        }
        tableBookingAdapter.clearAll();
        tableBookingAdapter.addAllTableData(occupied_seats);
        tableBookingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, VenderTableDetail model, int position) {
        switch (view.getId()) {

            case R.id.textViewTable:
                if (model.getTableStatus() == 1) {
                    Snackbar.make(view, "This table is occupied", Snackbar.LENGTH_LONG).show();

                } else {
                    haldleTableBooking(model);

                }
                break;
        }
    }

    @Override
    public void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, VenderTableDetail model, int position) {

    }

    @Override
    public void onShowLastItem() {

    }


    private void haldleTableBooking(VenderTableDetail data) {
        try {
            int index = tableBookingAdapter.getList().indexOf(data);
            if (index != -1) {
                Object obj = tableBookingAdapter.getList().get(index);
                if (obj instanceof VenderTableDetail) {
                    VenderTableDetail tableModel = (VenderTableDetail) obj;
                    tableModel.setTableStatus(2);
                    if (selectedSeat > -1 && index != selectedSeat) {
                        VenderTableDetail previousSelected = (VenderTableDetail) tableBookingAdapter.getList().get(selectedSeat);
                        previousSelected.setTableStatus(0);
                        tableBookingAdapter.notifyItemChanged(selectedSeat);

                    }
                    tableBookingAdapter.notifyItemChanged(index);
                }
                if (index != selectedSeat) {
                    selectedSeat = index;

                } else {

                }
                Intent intent = new Intent(context, MemberValidationActivity.class);
                intent.putExtra("tableId", selectedSeat);
                intent.putExtra("selctedVenderId",selctedVenderId);
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
