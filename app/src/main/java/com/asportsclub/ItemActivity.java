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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.asportsclub.adapter.MenuItemAdapter;
import com.asportsclub.adapter.SelectedItemAdapter;
import com.asportsclub.rest.RequestModel.BillItem;
import com.asportsclub.rest.RequestModel.BillSaveApi;
import com.asportsclub.rest.Response.AuthenticateUserResponse;
import com.asportsclub.rest.Response.Item;
import com.asportsclub.rest.Response.ItemBillDetail;
import com.asportsclub.rest.Response.ItemHeaderModel;
import com.asportsclub.rest.Response.MembershipDetails;
import com.asportsclub.rest.Response.MenuItem;
import com.asportsclub.rest.Response.MenuItems;
import com.asportsclub.rest.Response.SaveBillResponse;
import com.asportsclub.rest.Response.SubMenuItem;
import com.asportsclub.rest.Response.VenderTableDetail;
import com.asportsclub.rest.RestCallBack;
import com.asportsclub.rest.RestServiceFactory;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.utils.AppContext;
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
    List<Item> mSelectedItemListUsed = new ArrayList<>();
    List<Item> mSelectedItemListUnUsed = new ArrayList<>();


    int tableId,selctedVenderId;
    MembershipDetails membershipDetails;
    ItemBillDetail itemBillDetail;
    TextView txtTotalValue,text_signin,txtClosingBalanceValue,textOpeningBalance;
    EditText edtPax;
    private RelativeLayout layoutTotal;
    private LinearLayout layoutNoData,linearOpeningBalnce,linearClosingBalanceLayout;
    private Button btn_proceed;
    private Context context;
    private ImageView imageViewLogout,imageViewSetting,imageviewSearch;
    private VenderTableDetail mTableDetail;
    private int billnumber=0;
    private String selectvenderName;
    private boolean savebill;
    private MenuItems mMenuItes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_item);
        context=this;
        tableId = getIntent().getIntExtra("tableId",0);
        selectvenderName=getIntent().getStringExtra("vendername");
        selctedVenderId = getIntent().getIntExtra("selctedVenderId",0);
        membershipDetails = (MembershipDetails) getIntent().getSerializableExtra("memberDetail");
        mTableDetail = (VenderTableDetail) getIntent().getSerializableExtra("tableDetail");



        recyclerItemView = (RecyclerView)findViewById(R.id.itemView);
        itemSelectedView = (RecyclerView)findViewById(R.id.itemSelectedView);
        txtTotalValue = (TextView)findViewById(R.id.txtTotalValue);
        layoutTotal = (RelativeLayout)findViewById(R.id.layoutTotal);
        layoutNoData = (LinearLayout) findViewById(R.id.layoutNoData);
        txtTotalValue = (TextView) findViewById(R.id.txtTotalValue);
        edtPax = (EditText)findViewById(R.id.edt_pax);
        btn_proceed = (Button)findViewById(R.id.btn_proceed);
        imageViewSetting=(ImageView)findViewById(R.id.imageviewSetting);
        imageViewLogout=(ImageView)findViewById(R.id.imageviewLogout);
        imageviewSearch=(ImageView)findViewById(R.id.imageviewSearch);
        text_signin=(TextView)findViewById(R.id.text_signin);
        txtClosingBalanceValue=(TextView)findViewById(R.id.txtClosingBalanceValue);
        textOpeningBalance=(TextView)findViewById(R.id.textOpeningBalance);
        linearOpeningBalnce=(LinearLayout)findViewById(R.id.linearOpeningBalnce);
        linearClosingBalanceLayout=(LinearLayout)findViewById(R.id.linearClosingBalanceLayout);


        btn_proceed.setOnClickListener(this);
        imageviewSearch.setOnClickListener(this);




        mSelectedItemAdapter = new SelectedItemAdapter(ItemActivity.this,false,this);
        itemSelectedView.setLayoutManager(new LinearLayoutManager(ItemActivity.this));
        itemSelectedView.setHasFixedSize(false);
        itemSelectedView.setAdapter(mSelectedItemAdapter);
        itemSelectedView.setItemAnimator(new DefaultItemAnimator());
        mSelectedItemAdapter.addHeader(new ItemHeaderModel("Name","PRICE","QTY","GST","FINAL PRICE"));
        if(getIntent().hasExtra("billDetails")) {
            itemBillDetail = (ItemBillDetail) getIntent().getSerializableExtra("billDetails");
            billnumber=itemBillDetail.getBillDetails().getBillNumber();
            edtPax.setText(itemBillDetail.getBillDetails().getPAX()+"");
            membershipDetails=itemBillDetail.getBillDetails().getMembershipDetails();
            for(int i=0;i<itemBillDetail.getBillDetails().getItemDetails().size();i++){
                Item item=new Item();
                item.setItemName(itemBillDetail.getBillDetails().getItemDetails().get(i).getItemName());
                item.setItemCode(itemBillDetail.getBillDetails().getItemDetails().get(i).getItemCode());
                item.setItemQuantity(itemBillDetail.getBillDetails().getItemDetails().get(i).getQuantity());
                item.setItemRate(itemBillDetail.getBillDetails().getItemDetails().get(i).getItemRate());
                item.setTaxPercentage(itemBillDetail.getBillDetails().getItemDetails().get(i).getVatRate());
                item.setServiceCharge(itemBillDetail.getBillDetails().getItemDetails().get(i).getServiceCharge());
                item.setOrderedQuantity(itemBillDetail.getBillDetails().getItemDetails().get(i).getQuantity());
                item.setItemOrderStatus(true);
                item.setItemUnEditable(true);
                mSelectedItemListUsed.add(item);
                mSelectedItemListUnUsed.add(item);
            }
            mSelectedItemAdapter.addAllItem(mSelectedItemListUsed);
            mSelectedItemAdapter.notifyDataSetChanged();
        }
        recyclerItemView.setLayoutManager(new LinearLayoutManager(ItemActivity.this));
        recyclerItemView.setHasFixedSize(false);
        menuItemAdapter = new MenuItemAdapter(ItemActivity.this,false,this,membershipDetails);
        recyclerItemView.setAdapter(menuItemAdapter);
        recyclerItemView.setItemAnimator(new DefaultItemAnimator());
        if(membershipDetails!=null)
            text_signin.setText(selectvenderName+"("+membershipDetails.getMembershipId()+")");
        else
            text_signin.setText(selectvenderName+"("+itemBillDetail.getBillDetails().getMembershipDetails().getMembershipId()+")");
        handleItemAndTotal();
        hitGetItemList();
        if(membershipDetails.getMemberType().equalsIgnoreCase("M")||membershipDetails.getMemberType().equalsIgnoreCase("S")){
            linearClosingBalanceLayout.setVisibility(View.GONE);
            linearOpeningBalnce.setVisibility(View.GONE);
        }
        else{
            linearClosingBalanceLayout.setVisibility(View.VISIBLE);
            linearOpeningBalnce.setVisibility(View.VISIBLE);
        }
        RefrenceWrapper.getRefrenceWrapper(context).getFontTypeFace().setRobotoBoldTypeFace(context,txtTotalValue);
        imageViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showBasicwithTwoButton(context, "Edit URL","Are you sure want to change app configuration URL.", "Yes", "Cancel",new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        AppSharedPreferences.getInstance().setUrl("");
                        AppSharedPreferences.getInstance().setuserName("");
                        AppSharedPreferences.getInstance().setValidurl("no");
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
        textOpeningBalance.setText(""+membershipDetails.getOpeningBalance());
        setClosingBalance();
        AppContext.getInstance().setGetItemTotal(getItemTotal());
    }
    private void setClosingBalance(){
        txtClosingBalanceValue.setText(String.format("%.2f",(membershipDetails.getOpeningBalance()-getItemTotal())));
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
                mMenuItes = response;
                if(mSelectedItemListUnUsed!=null&&mSelectedItemListUnUsed.size()>0){
                    for (MenuItem menuItem:mMenuItes.getMenuItems()) {
                        if(menuItem.getSubMenuItems().size()>0){
                            for (SubMenuItem subMenuItem:menuItem.getSubMenuItems()) {
                                if(subMenuItem.getItems().size()>0){
                                    for (Item item:subMenuItem.getItems()) {
                                        for (Item selected:mSelectedItemListUsed) {
                                            if(selected.getItemCode()==item.getItemCode()){
                                               // item.setItemQuantity(selected.getItemQuantity());
                                                //item.setOrderedQuantity(selected.getItemQuantity());
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                    menuItemAdapter.addAllItem(mMenuItes.getMenuItems());

                }
                else{
                    menuItemAdapter.addAllItem(response.getMenuItems());

                }



            }
        });
    }

    @Override
    public void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, Item model, int position) {

        switch (view.getId()) {
            case R.id.minusButton:
                removeItems(model);
                break;
            case R.id.plusButton:
                addItem(model);
                break;
        }
    }

    @Override
    public void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, Item model, int position) {

    }

    @Override
    public void onShowLastItem() {

    }

    private void removeItems(Item model){
        int itemPosition = -1;
        if(model.isItemOrderStatus()){
            ToastUtils.show(context,"Can't remove items that has been ordered");
        }else{
            for(int i=0;i<mSelectedItemListUnUsed.size();i++){
                if(model.getItemCode() == mSelectedItemListUnUsed.get(i).getItemCode()&&!mSelectedItemListUnUsed.get(i).isItemUnEditable()){
                    itemPosition = i+1;
                    break;
                }
            }
            if(model.getItemQuantity()>0){
                if(itemPosition > -1){
                    Item selectedItem = (Item) mSelectedItemAdapter.getItem(itemPosition);
                    if(model.isItemUnEditable()&&model.getOrderedQuantity()>0&&model.getOrderedQuantity() == model.getItemQuantity()){
                        selectedItem.setItemOrderStatus(true);
                        selectedItem.setItemUnEditable(true);
                    }
                    selectedItem.setItemQuantity(model.getItemQuantity());
                    mSelectedItemAdapter.notifyItemChanged(itemPosition);
                }
            }
            else{
                mSelectedItemAdapter.removeItem(itemPosition);
                mSelectedItemListUnUsed.remove(itemPosition-1);
                mSelectedItemListUsed.remove(itemPosition-1);

            }
            handleItemAndTotal();
        }
        AppContext.getInstance().setGetItemTotal(getItemTotal());

    }
    private void addItem(Item model){
        int itemPosition = -1;
//        double itemsPrice = handleItemAndTotalBeforeAdd(model);
//        if(membershipDetails.getMemberType().equalsIgnoreCase("D")||membershipDetails.getMemberType().equalsIgnoreCase("X")){
//            if(itemsPrice > membershipDetails.getOpeningBalance()){
//                ToastUtils.show(context,"You can't add this item your balace is low.");
//                return;
//            }
//        }
        for(int i=0;i<mSelectedItemListUnUsed.size();i++){
            if(model.getItemCode() == mSelectedItemListUnUsed.get(i).getItemCode()&&!mSelectedItemListUnUsed.get(i).isItemUnEditable()){
                itemPosition = i+1;
            }
        }
        if(model.getItemQuantity()>0){
            if(itemPosition > -1){
                Item selectedItem = (Item) mSelectedItemAdapter.getItem(itemPosition);
                selectedItem.setItemQuantity(model.getItemQuantity());
                selectedItem.setItemOrderStatus(false);
                mSelectedItemAdapter.notifyItemChanged(itemPosition);



            }else{
                int itmQty=model.getItemQuantity();
                //for(int i=0;i<mSelectedItemListUnUsed.size();i++){
                    //if(model.getItemCode() == mSelectedItemListUnUsed.get(i).getItemCode()&&mSelectedItemListUnUsed.get(i).isItemUnEditable()){
                  //      itmQty = model.getItemQuantity()-mSelectedItemListUnUsed.get(i).getItemQuantity();
                //    }
              //  }
                Item itm=model;
                itm.setItemOrderStatus(false);
                itm.setItemQuantity(itmQty);
                itm.setOrderedQuantity(0);
                mSelectedItemListUnUsed.add(0,itm);
                mSelectedItemListUsed.add(0,itm);
                mSelectedItemAdapter.addItem(itm);
                mSelectedItemAdapter.notifyDataSetChanged();

            }
        }
        else{
            mSelectedItemAdapter.removeItem(itemPosition);
        }
        handleItemAndTotal();
        AppContext.getInstance().setGetItemTotal(getItemTotal());

    }

    public void handleItemAndTotal(){
        if(mSelectedItemAdapter.getItemCount()>1){
            itemSelectedView.setVisibility(View.VISIBLE);
                    layoutTotal.setVisibility(View.VISIBLE);
            layoutNoData.setVisibility(View.GONE);
            double totalValue=0;
            for (Object item:mSelectedItemListUnUsed) {
                if(item instanceof Item){
                    Item model = (Item) item;
                    double finalprice = (((model.getItemRate() * model.getItemQuantity())*model.getServiceCharge())/100)+(((model.getItemRate() * model.getItemQuantity())*model.getTaxPercentage())/100) + (model.getItemRate() * model.getItemQuantity());
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
        setClosingBalance();

    }
    public double handleItemAndTotalBeforeAdd(Item itemToAdd){
        if(mSelectedItemAdapter.getItemCount()>1){
            double totalValue=getItemTotal();
            double finalprice = (((itemToAdd.getItemRate() * 1)*itemToAdd.getServiceCharge())/100)+(((itemToAdd.getItemRate() * 1)*itemToAdd.getTaxPercentage())/100) + (itemToAdd.getItemRate() * 1);

            totalValue = totalValue + finalprice;
            return totalValue;
        }
     return 0;
    }
    public double getItemTotal(){
        if(mSelectedItemAdapter.getItemCount()>1){
            double totalValue=0;
            for (Object item:mSelectedItemListUnUsed) {
                if(item instanceof Item){
                    Item model = (Item) item;
                        double finalprice = (((model.getItemRate() * model.getItemQuantity())*model.getServiceCharge())/100)+(((model.getItemRate() * model.getItemQuantity())*model.getTaxPercentage())/100) + (model.getItemRate() * model.getItemQuantity());

                        totalValue += finalprice;

                }
            }
            return totalValue;
        }
        return 0.0;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_proceed:
                if (mSelectedItemAdapter.getItemCount() > 1) {
                    if(edtPax.getText().toString()!=null&&edtPax.getText().toString().length()>0) {
                        AuthenticateUserResponse userRespose = AppSharedPreferences.getInstance().getTableInfo();
                        List<BillItem> billItems = new ArrayList<>();
                        for (Object item : mSelectedItemAdapter.getList()) {
                            if (item instanceof Item) {
                                Item model = (Item) item;
                                if(!model.isItemOrderStatus()){
                                    double finalprice = (((model.getItemRate() * (model.getItemQuantity()-model.getOrderedQuantity())) * model.getServiceCharge()) / 100) + (((model.getItemRate() * (model.getItemQuantity()-model.getOrderedQuantity())) * model.getTaxPercentage()) / 100) + (model.getItemRate() * (model.getItemQuantity()-model.getOrderedQuantity()));
                                    billItems.add(new BillItem(model.getItemCode(), model.getUnitCode(), (model.getItemQuantity()-model.getOrderedQuantity()), model.getItemRate(), finalprice, (model.getTaxPercentage()),( model.getServiceCharge()) , model.getItemName(), Integer.parseInt(userRespose.getUserDetail().getUserId()), Integer.parseInt(AppSharedPreferences.getInstance().getvendorID())));

                                }

                            }
                        }
                        double itemTotal = getItemTotal();
                        double itemTotalDecimal = getItemTotal() % 1;
                        if(billItems.size()==0){
                            ToastUtils.show(context,"Please add items before proceeding order");
                           return;
                        }

                        if(itemTotal==0){
                            ToastUtils.show(context,"You already placed your order.Add some items to update your order.");
                        }else{
                            BillSaveApi requestBillSave = new BillSaveApi(billnumber, Integer.parseInt(AppSharedPreferences.getInstance().getvendorID()), Integer.parseInt(userRespose.getUserDetail().getUserId()), membershipDetails.getMembershipId(), membershipDetails.getMemberType(), membershipDetails.getOpeningBalance(), mTableDetail.getTableId(), Integer.parseInt(edtPax.getText().toString()), membershipDetails.getCouponNumber(), itemTotal, itemTotalDecimal, billItems);
                            hitBillSaveApi(requestBillSave);
                        }

                    }else{
                        ToastUtils.show(context,"Please insert PAX value");
                    }
        }
        else {
                    ToastUtils.show(context,"Please add items before proceeding order");

                }

                break;
            case R.id.imageviewSearch:
                Intent i = new Intent(context, SearchItemActivity.class);
                i.putExtra("menuItem",mMenuItes);
                i.putExtra("memberDetail",membershipDetails);
                i.putExtra("itemsTotal",getItemTotal());
                startActivityForResult(i,1001);
                break;
        }
    }
    private void hitBillSaveApi(BillSaveApi requestBillSave) {


        Call<SaveBillResponse> commentsCall = RestServiceFactory.createService().saveBill(requestBillSave);
        commentsCall.enqueue(new RestCallBack<SaveBillResponse>() {
            @Override
            public void onFailure(Call<SaveBillResponse> call, String message) {
                ToastUtils.show(ItemActivity.this,message);
            }

            @Override
            public void onResponse(Call<SaveBillResponse> call, Response<SaveBillResponse> restResponse, SaveBillResponse response) {
                if(response.getStatusCode()!=null&&response.getStatusCode().getErrorCode()==0){
                    billnumber = response.getBillNumber();
                   // ToastUtils.show(context,"Your order placed successfully.Your order bill number is "+response.getBillNumber()+" .");
                    for(int i=0;i<mSelectedItemAdapter.getItemCount();i++){
                        if(mSelectedItemAdapter.getItem(i) instanceof Item){
                            Item selectedItem = (Item) mSelectedItemAdapter.getItem(i);
                            selectedItem.setItemOrderStatus(true);
                            selectedItem.setItemUnEditable(true);
                            selectedItem.setOrderedQuantity(selectedItem.getItemQuantity());
                            mSelectedItemAdapter.notifyItemChanged(i);
                            savebill=true;
                            DialogUtils.showBasicMessage(context, "Your order placed successfully.Your order bill number is " + response.getBillNumber() + " .", new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    Intent in=new Intent();
                                    in.putExtra("billNo",billnumber);
                                    setResult(RESULT_OK,in);
                                    finish();
                                }
                            });

                        }
                    }

                }
                else if(response.getStatusCode()!=null&&response.getStatusCode().getErrorCode()!=0){
                    ToastUtils.show(context,response.getStatusCode().getErrorMessage());

                }
                else {
                    ToastUtils.show(context,"Some error occured while proceeding order.");

                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode == 1001)

        {
            if(data!=null){
                ArrayList<Item> list = (ArrayList<Item>) data.getSerializableExtra("selectedItems");


                for (Item model:list) {
                    setupSearchItemList(model);
                    //addItemFromSearch(model);

                }
                menuItemAdapter.clearAll();

                menuItemAdapter.addAllItem(mMenuItes.getMenuItems());
                menuItemAdapter.notifyDataSetChanged();


            }

        }
    }

    private void setupSearchItemList(Item model){
        for (MenuItem menuItem:mMenuItes.getMenuItems()) {
            if(menuItem.getSubMenuItems().size()>0){
                for (SubMenuItem subMenuItem:menuItem.getSubMenuItems()) {
                    if(subMenuItem.getItems().size()>0){
                        for (Item item:subMenuItem.getItems()) {
                            if(model.getItemCode()==item.getItemCode()){
                                item.setItemQuantity(item.getItemQuantity()+model.getItemQuantity());
                                addItem(item);

                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void onBackPressed() {
        if(!getIntent().hasExtra("billDetails")&&savebill){
            Intent i=new Intent();
            i.putExtra("billNo",billnumber);
            setResult(RESULT_OK,i);
        }else {
            finish();
        }
        super.onBackPressed();

    }
}
