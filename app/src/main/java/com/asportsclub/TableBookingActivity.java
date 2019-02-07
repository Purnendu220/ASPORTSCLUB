package com.asportsclub;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.Toast;

import com.asportsclub.adapter.TableBookingAdapter;
import com.asportsclub.rest.Response.AuthenticateUserResponse;
import com.asportsclub.rest.Response.GlobalVenderDetail;
import com.asportsclub.rest.Response.UserValidVenderDetail;
import com.asportsclub.rest.Response.VenderTableDetail;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.utils.GridAutofitLayoutManager;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

public class TableBookingActivity extends AppCompatActivity implements AdapterCallbacks<VenderTableDetail> {

    RecyclerView recycler_table_view;
    TableBookingAdapter tableBookingAdapter;
    ArrayList<VenderTableDetail> list = new ArrayList<>();
    ArrayList<UserValidVenderDetail> mListVendorDetails = new ArrayList<>();
    MaterialSpinner filterSpinner1, filterSpinner2;
    int selectedSeat = -1;
    private AuthenticateUserResponse authenticateUserResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_booking);
        authenticateUserResponse = (AuthenticateUserResponse) getIntent().getSerializableExtra("vernderDetail");
        recycler_table_view = (RecyclerView) findViewById(R.id.recycler_table_view);
        filterSpinner1 = (MaterialSpinner) findViewById(R.id.spinner_filter_one);
        filterSpinner2 = (MaterialSpinner) findViewById(R.id.spinner_filter_two);

        recycler_table_view.setLayoutManager(new GridAutofitLayoutManager(TableBookingActivity.this, 120));
        recycler_table_view.setHasFixedSize(false);
        // recyclerViewClass.scrollToPosition(0);

        try {
            ((SimpleItemAnimator) recycler_table_view.getItemAnimator()).setSupportsChangeAnimations(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


        filterSpinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {


            }
        });

    }

    private void setTableData() {
        list = new ArrayList<>();
        list.addAll(authenticateUserResponse.getVenderTableDetails());
        mListVendorDetails.addAll(authenticateUserResponse.getUserValidVenderDetails());
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
