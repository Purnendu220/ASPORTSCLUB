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
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.utils.GridAutofitLayoutManager;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;

public class TableBookingActivity extends AppCompatActivity implements AdapterCallbacks<TableBookingModel> {

    RecyclerView recycler_table_view;
    TableBookingAdapter tableBookingAdapter;
    ArrayList<TableBookingModel> list=new ArrayList<>();
    MaterialSpinner filterSpinner1,filterSpinner2;
    int selectedSeat=-1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_booking);
        recycler_table_view=(RecyclerView)findViewById(R.id.recycler_table_view);
        filterSpinner1 = (MaterialSpinner) findViewById(R.id.spinner_filter_one);
        filterSpinner2 = (MaterialSpinner) findViewById(R.id.spinner_filter_two);

        recycler_table_view.setLayoutManager(new GridAutofitLayoutManager(TableBookingActivity.this,120));
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
        filterSpinner1.setItems("View All", "View Occupied", "View Available");

        filterSpinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
               switch (position){
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

        filterSpinner2.setItems("SELECT VENDORP", "COFFEE SHOP", "GARDEN VIEW BAR", "LOUNGE BAR", "RESTAURANT");

        filterSpinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {


            }
        });

    }

    private void setTableData(){
         list=new ArrayList<>();

        for(int i=0;i<50;i++){
            if(i%2==0){
                list.add(new TableBookingModel(i,i,0,"T"+i));

            }else{
                list.add(new TableBookingModel(i,i,1,"T"+i));

            }
        }
        tableBookingAdapter.clearAll();
        tableBookingAdapter.addAllTableData(list);
        tableBookingAdapter.notifyDataSetChanged();

    }

    private void viewAvailableSeats(){
        ArrayList<TableBookingModel> available_seats=new ArrayList<>();

        for (TableBookingModel model:list) {
            if(model.isTableStatus() == 0){
                available_seats.add(model);
            }
        }
        tableBookingAdapter.clearAll();
        tableBookingAdapter.addAllTableData(available_seats);
        tableBookingAdapter.notifyDataSetChanged();



    }
    private void viewOccupiedSeats(){
        ArrayList<TableBookingModel> occupied_seats=new ArrayList<>();

        for (TableBookingModel model:list) {
            if(model.isTableStatus() == 1){
                occupied_seats.add(model);
            }
        }
        tableBookingAdapter.clearAll();
        tableBookingAdapter.addAllTableData(occupied_seats);
        tableBookingAdapter.notifyDataSetChanged();
    }
    @Override
    public void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, TableBookingModel model, int position) {
        switch (view.getId()) {

            case R.id.textViewTable:
                if(model.isTableStatus()==1){
                    Snackbar.make(view, "This table is occupied", Snackbar.LENGTH_LONG).show();

                }else{
                    haldleTableBooking(model);

                }
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
                    tableModel.setTableStatus(2);
                    if(selectedSeat>-1&&index!=selectedSeat){
                        TableBookingModel previousSelected= (TableBookingModel) tableBookingAdapter.getList().get(selectedSeat);
                        previousSelected.setTableStatus(0);
                        tableBookingAdapter.notifyItemChanged(selectedSeat);

                    }
                    tableBookingAdapter.notifyItemChanged(index);
                }
                if(index!=selectedSeat){
                    selectedSeat=index;

                }else{

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
