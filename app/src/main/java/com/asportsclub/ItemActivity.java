package com.asportsclub;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.asportsclub.adapter.MenuItemAdapter;
import com.asportsclub.adapter.SelectedItemAdapter;
import com.asportsclub.rest.Response.Item;
import com.asportsclub.rest.Response.ItemBillDetail;
import com.asportsclub.rest.Response.ItemHeaderModel;
import com.asportsclub.rest.Response.MembershipDetails;
import com.asportsclub.rest.Response.MenuItems;
import com.asportsclub.rest.RestCallBack;
import com.asportsclub.rest.RestServiceFactory;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.utils.AppSharedPreferences;
import com.asportsclub.utils.DialogUtils;
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
    ItemBillDetail itemBillDetail;
    TextView txtTotalValue;
    private RelativeLayout layoutTotal;
    private LinearLayout layoutNoData;
    private Button btn_proceed;
    private Context context;
    private ImageView imageViewLogout,imageViewSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_item);
        context=this;
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
        imageViewSetting=(ImageView)findViewById(R.id.imageviewSetting);
        imageViewLogout=(ImageView)findViewById(R.id.imageviewLogout);
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
        if(getIntent().hasExtra("billDetails")) {
            itemBillDetail = (ItemBillDetail) getIntent().getSerializableExtra("billDetails");

            for(int i=0;i<itemBillDetail.getBillDetails().getItemDetails().size();i++){
                Item item=new Item();
                item.setItemName(itemBillDetail.getBillDetails().getItemDetails().get(i).getItemName());
                item.setItemCode(itemBillDetail.getBillDetails().getItemDetails().get(i).getItemCode());
                item.setItemQuantity(itemBillDetail.getBillDetails().getItemDetails().get(i).getQuantity());
                item.setItemRate(itemBillDetail.getBillDetails().getItemDetails().get(i).getItemRate());
                item.setTaxPercentage(itemBillDetail.getBillDetails().getItemDetails().get(i).getVatAmount());
                mSelectedItemList.add(item);
            }
            mSelectedItemAdapter.addAllItem(mSelectedItemList);
            mSelectedItemAdapter.notifyDataSetChanged();
        }
        handleItemAndTotal();
        hitGetItemList();
        RefrenceWrapper.getRefrenceWrapper(context).getFontTypeFace().setRobotoBoldTypeFace(context,txtTotalValue);
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
