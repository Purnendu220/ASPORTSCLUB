package com.asportsclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.asportsclub.adapter.ItemAdapter;
import com.asportsclub.rest.Response.Item;
import com.asportsclub.rest.Response.ItemBillDetail;
import com.asportsclub.rest.Response.MembershipDetails;
import com.asportsclub.rest.Response.MenuItem;
import com.asportsclub.rest.Response.MenuItems;
import com.asportsclub.rest.Response.SubMenuItem;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.utils.LogUtils;
import com.asportsclub.utils.ToastUtils;

import java.util.ArrayList;

import ru.nikartm.support.ImageBadgeView;

public class SearchItemActivity extends AppCompatActivity implements AdapterCallbacks<Item> , View.OnClickListener {


    RecyclerView itemView;
    MenuItems menuItem;
    EditText edt_search;
    ImageBadgeView imageviewSetting;
    TextView textCancel;
    private int SEARCH_LIMIT = 2;
    ItemAdapter itemAdapter;
    ArrayList<Item> itemList = new ArrayList<>();
    MembershipDetails membershipDetails;
    double selcetedItemsTotal;
    ArrayList<Item> mSelectedItems = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_search_item);
        itemView = (RecyclerView)findViewById(R.id.itemView);
        imageviewSetting = (ImageBadgeView)findViewById(R.id.imageviewSetting);
        textCancel = (TextView)findViewById(R.id.textCancel);

        menuItem = (MenuItems) getIntent().getSerializableExtra("menuItem");
        membershipDetails = (MembershipDetails) getIntent().getSerializableExtra("memberDetail");
        selcetedItemsTotal = (double) getIntent().getDoubleExtra("itemsTotal",0);
        imageviewSetting.setOnClickListener(this);
        textCancel.setOnClickListener(this);


        edt_search = (EditText)findViewById(R.id.edt_search);

        itemView.setVisibility(View.VISIBLE);
        itemView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter = new ItemAdapter(this,false,this);

        itemView.setHasFixedSize(false);
        setupSearchItemList();
        setItemAdapter("");


        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(final Editable editable) {
                if (editable.toString().length() >= SEARCH_LIMIT) {
                    setItemAdapter(editable.toString().toLowerCase());

                }else{
                    setItemAdapter("");

                }

            }
        });



    }

    private void setItemAdapter(String filter){
        itemAdapter.clearAll();
        itemView.setAdapter(itemAdapter);
        itemView.setItemAnimator(new DefaultItemAnimator());
        itemAdapter.addAllItem(getFilteredList(filter));

    }



    private void setupSearchItemList(){
        itemList = new ArrayList<>();
        for (MenuItem menuItem:menuItem.getMenuItems()) {
            if(menuItem.getSubMenuItems().size()>0){
                for (SubMenuItem subMenuItem:menuItem.getSubMenuItems()) {
                    if(subMenuItem.getItems().size()>0){
                        for (Item item:subMenuItem.getItems()) {
                            item.setItemQuantity(0);
                            itemList.add(item);
                        }
                    }
                }
            }
        }
    }

    private ArrayList<Item> getFilteredList(String filter){
      ArrayList<Item>  filteredList = new ArrayList<>();
      if(filter!=null&&filter.length()>0){
          for (Item item:itemList) {
              if(item.getItemName().toLowerCase().contains(filter)){
                  filteredList.add(item);
              }

          }
          return filteredList;
      }else{
          for (Item item:itemList) {
                  filteredList.add(item);


          }
          return filteredList;
      }


    }

    @Override
    public void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, Item model, int position) {
        switch (view.getId()) {
            case R.id.minusButton:
                LogUtils.debug("Minus Item Clicked");
                removeItems(model);
                setBadge();
                break;
            case R.id.plusButton:
                LogUtils.debug("Plus Item Clicked");
                addItem(model);
                setBadge();
                break;
        }
    }

    @Override
    public void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, Item model, int position) {

    }

    @Override
    public void onShowLastItem() {

    }

    private void setBadge(){
        if(mSelectedItems!=null&&mSelectedItems.size()>0){
            imageviewSetting.setBadgeValue(mSelectedItems.size());
        }
        else{
            imageviewSetting.setBadgeValue(0);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageviewSetting:
                Intent intent=new Intent();
                intent.putExtra("selectedItems",mSelectedItems);
                setResult(1001,intent);
                finish();

                break;
            case R.id.textCancel:
                setResult(1001);
                finish();
               break;

        }

    }

    private void removeItems(Item model){
        int itemPosition = -1;
        if(model.isItemOrderStatus()){
            ToastUtils.show(this,"Can't remove items that has been ordered");
        }else{
            for(int i=0;i<mSelectedItems.size();i++){
                if(model.getItemCode() == mSelectedItems.get(i).getItemCode()){
                    itemPosition = i;
                }
            }
            if(model.getItemQuantity()>0){
                if(itemPosition > -1){
                    Item selectedItem = mSelectedItems.get(itemPosition);
                    selectedItem.setItemQuantity(model.getItemQuantity());
                    mSelectedItems.set(itemPosition,selectedItem);



                }else{
                    model.setItemOrderStatus(false);
                    mSelectedItems.add(model);

                }
            }
            else{
                mSelectedItems.remove(itemPosition);


            }
        }
    }
    private void addItem(Item model){
        int itemPosition = -1;
        double itemsPrice = handleItemAndTotalBeforeAdd(model);
        if(membershipDetails.getMemberType()=="D"||membershipDetails.getMemberType()=="X"){
            if(itemsPrice > membershipDetails.getOpeningBalance()){
                ToastUtils.show(this,"You can't add this item your balace is low.");
                return;
            }
        }
        for(int i=0;i<mSelectedItems.size();i++){
            if(model.getItemCode() == mSelectedItems.get(i).getItemCode()){
                itemPosition = i;
            }
        }
        if(model.getItemQuantity()>0){
            if(itemPosition > -1){
                Item selectedItem = mSelectedItems.get(itemPosition);
                selectedItem.setItemQuantity(model.getItemQuantity());
                selectedItem.setItemOrderStatus(false);
                mSelectedItems.set(itemPosition,selectedItem);

            }else{
                model.setItemOrderStatus(false);
                mSelectedItems.add(model);
            }
        }
        else{
            mSelectedItems.remove(itemPosition);
        }

    }

    public double handleItemAndTotalBeforeAdd(Item itemToAdd){
        double finalPriceToreturn = selcetedItemsTotal;
        double finalprice = (((itemToAdd.getItemRate() * itemToAdd.getItemQuantity())*itemToAdd.getServiceCharge())/100)+(((itemToAdd.getItemRate() * itemToAdd.getItemQuantity())*itemToAdd.getTaxPercentage())/100) + (itemToAdd.getItemRate() * itemToAdd.getItemQuantity());
        finalPriceToreturn += finalprice;
        return finalPriceToreturn;
    }
    public double getItemTotal(){
            double totalValue=selcetedItemsTotal;
            for (Item item:mSelectedItems) {
                    Item model = (Item) item;
                    double finalprice = (((model.getItemRate() * model.getItemQuantity())*model.getServiceCharge())/100)+(((model.getItemRate() * model.getItemQuantity())*model.getTaxPercentage())/100) + (model.getItemRate() * model.getItemQuantity());
                    totalValue += finalprice;

            }
            return totalValue;
    }

}
