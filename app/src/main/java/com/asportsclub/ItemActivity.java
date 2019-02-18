package com.asportsclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asportsclub.adapter.MenuItemAdapter;
import com.asportsclub.adapter.SelectedItemAdapter;
import com.asportsclub.rest.Response.Item;
import com.asportsclub.rest.Response.ItemHeaderModel;
import com.asportsclub.rest.Response.MembershipDetails;
import com.asportsclub.rest.Response.MenuItems;
import com.asportsclub.rest.RestCallBack;
import com.asportsclub.rest.RestServiceFactory;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.utils.ToastUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ItemActivity extends AppCompatActivity implements AdapterCallbacks<Item>, View.OnClickListener {

    MenuItemAdapter menuItemAdapter;
    SelectedItemAdapter mSelectedItemAdapter;
    RecyclerView recyclerItemView,itemSelectedView;
    List<Item> mSelectedItemList = new ArrayList<>();
    int tableId,selctedVenderId;
    MembershipDetails membershipDetails;
    TextView txtTotalValue;
    private RelativeLayout layoutTotal;
    private LinearLayout layoutNoData;
    private Button btn_proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        tableId = getIntent().getIntExtra("tableId",0);
        selctedVenderId = getIntent().getIntExtra("selctedVenderId",0);
        membershipDetails = (MembershipDetails) getIntent().getSerializableExtra("memberDetail");


        recyclerItemView = (RecyclerView)findViewById(R.id.itemView);
        itemSelectedView = (RecyclerView)findViewById(R.id.itemSelectedView);
        txtTotalValue = (TextView)findViewById(R.id.txtTotalValue);
        layoutTotal = (RelativeLayout)findViewById(R.id.layoutTotal);
        layoutNoData = (LinearLayout) findViewById(R.id.layoutNoData);
        txtTotalValue = (TextView) findViewById(R.id.txtTotalValue);
        btn_proceed = (Button)findViewById(R.id.btn_proceed);
        btn_proceed.setOnClickListener(this);

        recyclerItemView.setLayoutManager(new LinearLayoutManager(ItemActivity.this));
        recyclerItemView.setHasFixedSize(false);
        menuItemAdapter = new MenuItemAdapter(ItemActivity.this,false,this);
        recyclerItemView.setAdapter(menuItemAdapter);
        recyclerItemView.setItemAnimator(new DefaultItemAnimator());

        mSelectedItemAdapter = new SelectedItemAdapter(ItemActivity.this,false,this);
        itemSelectedView.setLayoutManager(new LinearLayoutManager(ItemActivity.this));
        itemSelectedView.setHasFixedSize(false);
        itemSelectedView.setAdapter(mSelectedItemAdapter);
        itemSelectedView.setItemAnimator(new DefaultItemAnimator());
        mSelectedItemAdapter.addHeader(new ItemHeaderModel("Name","PRICE","QTY","GST","FINAL PRICE"));
        handleItemAndTotal();
        hitGetItemList();
    }

    private void hitGetItemList() {


        Call<MenuItems> commentsCall = RestServiceFactory.createService().getItemList(selctedVenderId);
        commentsCall.enqueue(new RestCallBack<MenuItems>() {
            @Override
            public void onFailure(Call<MenuItems> call, String message) {
                ToastUtils.show(ItemActivity.this,message);
            }

            @Override
            public void onResponse(Call<MenuItems> call, Response<MenuItems> restResponse, MenuItems response) {
                menuItemAdapter.addAllItem(response.getMenuItems());

            }
        });
    }

    @Override
    public void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, Item model, int position) {
        int itemPosition = -1;

        switch (view.getId()) {
            case R.id.minusButton:
                for(int i=0;i<mSelectedItemList.size();i++){
                    if(model.getItemCode() == mSelectedItemList.get(i).getItemCode()){
                        itemPosition = i+1;
                    }
                }
                if(model.getItemQuantity()>0){
                    if(itemPosition > -1){
                        Item selectedItem = (Item) mSelectedItemAdapter.getItem(itemPosition);
                        selectedItem.setItemQuantity(model.getItemQuantity());
                        mSelectedItemAdapter.notifyItemChanged(itemPosition);



                    }else{
                        mSelectedItemList.add(model);
                        mSelectedItemAdapter.addItem(model);
                        mSelectedItemAdapter.notifyDataSetChanged();

                    }
                }
                else{
                    mSelectedItemAdapter.removeItem(itemPosition);
                    mSelectedItemList.remove(itemPosition-1);

                }
                handleItemAndTotal();
                break;
            case R.id.plusButton:
                for(int i=0;i<mSelectedItemList.size();i++){
                    if(model.getItemCode() == mSelectedItemList.get(i).getItemCode()){
                        itemPosition = i+1;
                    }
                }
                if(model.getItemQuantity()>0){
                    if(itemPosition > -1){
                        Item selectedItem = (Item) mSelectedItemAdapter.getItem(itemPosition);
                        selectedItem.setItemQuantity(model.getItemQuantity());
                        mSelectedItemAdapter.notifyItemChanged(itemPosition);


                    }else{
                        mSelectedItemList.add(model);
                        mSelectedItemAdapter.addItem(model);
                        mSelectedItemAdapter.notifyDataSetChanged();

                    }
                }
                else{
                    mSelectedItemAdapter.removeItem(itemPosition);
                }
                handleItemAndTotal();
                break;
        }
    }

    @Override
    public void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, Item model, int position) {

    }

    @Override
    public void onShowLastItem() {

    }

    public void handleItemAndTotal(){
        if(mSelectedItemAdapter.getItemCount()>1){
            itemSelectedView.setVisibility(View.VISIBLE);
                    layoutTotal.setVisibility(View.VISIBLE);
            layoutNoData.setVisibility(View.GONE);
            double totalValue=0;
            for (Object item:mSelectedItemAdapter.getList()) {
                if(item instanceof Item){
                    Item model = (Item) item;
                    double finalprice = (((model.getItemRate() * model.getItemQuantity())*model.getTaxPercentage())/100) + (model.getItemRate() * model.getItemQuantity());
                    //+"";

                    totalValue += finalprice;
                }
            }
            txtTotalValue.setText(new DecimalFormat("##.##").format(totalValue));
        }else{


            itemSelectedView.setVisibility(View.GONE);
            layoutTotal.setVisibility(View.GONE);
            layoutNoData.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_proceed:
                break;
        }
    }
}
