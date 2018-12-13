package com.asportsclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;

import com.asportsclub.adapter.TableBookingAdapter;
import com.asportsclub.utils.AdapterCallbacks;

import java.util.ArrayList;

public class TableBookingActivity extends AppCompatActivity implements AdapterCallbacks<TableBookingModel> {

    RecyclerView recycler_table_view;
    TableBookingAdapter tableBookingAdapter;
    ArrayList<TableBookingModel> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_booking);
        recycler_table_view=(RecyclerView)findViewById(R.id.recycler_table_view);
        recycler_table_view.setLayoutManager(new GridLayoutManager(TableBookingActivity.this,10));
        recycler_table_view.setHasFixedSize(false);
        // recyclerViewClass.scrollToPosition(0);

        try {
            ((SimpleItemAnimator) recycler_table_view.getItemAnimator()).setSupportsChangeAnimations(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableBookingAdapter = new TableBookingAdapter(TableBookingActivity.this,false,this);
        recycler_table_view.setAdapter(tableBookingAdapter);
        setTableData();


    }

    private void setTableData(){
        for(int i=0;i<50;i++){
            list.add(new TableBookingModel(i,i,true,"T"+i));
        }
        tableBookingAdapter.addAllTableData(list);
    }

    @Override
    public void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, TableBookingModel model, int position) {
        switch (view.getId()) {

            case R.id.textViewTable:
                haldleTableBooking(model);
                break;
        }
    }

    @Override
    public void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, TableBookingModel model, int position) {

    }

    @Override
    public void onShowLastItem() {

    }


    private void haldleTableBooking(TableBookingModel data) {
        try {
            int index = tableBookingAdapter.getList().indexOf(data);
            if (index != -1) {
                Object obj = tableBookingAdapter.getList().get(index);
                if (obj instanceof TableBookingModel) {
                    TableBookingModel tableModel = (TableBookingModel) obj;
                    tableModel.setTableStatus(!tableModel.isTableStatus());
                    tableBookingAdapter.notifyItemChanged(index);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
