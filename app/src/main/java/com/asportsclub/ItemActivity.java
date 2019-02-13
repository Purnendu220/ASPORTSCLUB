package com.asportsclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.asportsclub.adapter.GlobalConfigAdapter;
import com.asportsclub.adapter.MenuItemAdapter;
import com.asportsclub.adapter.SelectedItemAdapter;
import com.asportsclub.rest.Response.GlobalVenderDetail;
import com.asportsclub.rest.Response.GlobalVenderDetails;
import com.asportsclub.rest.Response.Item;
import com.asportsclub.rest.Response.MenuItem;
import com.asportsclub.rest.Response.MenuItems;
import com.asportsclub.rest.RestCallBack;
import com.asportsclub.rest.RestServiceFactory;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.utils.AppContext;
import com.asportsclub.utils.GridAutofitLayoutManager;
import com.asportsclub.utils.ToastUtils;
import com.brandongogetap.stickyheaders.StickyLayoutManager;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ItemActivity extends AppCompatActivity implements AdapterCallbacks<Item> {

    MenuItemAdapter menuItemAdapter;
    SelectedItemAdapter mSelectedItemAdapter;
    RecyclerView recyclerItemView,itemSelectedView;
    List<Item> mSelectedItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        AppContext.getInstance().setContext(getApplication());

        recyclerItemView = (RecyclerView)findViewById(R.id.itemView);
        itemSelectedView = (RecyclerView)findViewById(R.id.itemSelectedView);

        recyclerItemView.setLayoutManager(new LinearLayoutManager(ItemActivity.this));
        recyclerItemView.setHasFixedSize(false);
        menuItemAdapter = new MenuItemAdapter(ItemActivity.this,false,this);
        recyclerItemView.setAdapter(menuItemAdapter);
        recyclerItemView.setItemAnimator(new DefaultItemAnimator());

        mSelectedItemAdapter = new SelectedItemAdapter(ItemActivity.this,false,this);
        StickyLayoutManager layoutManager = new StickyLayoutManager(ItemActivity.this, mSelectedItemAdapter);
        layoutManager.elevateHeaders(true);
        layoutManager.elevateHeaders((int) getResources().getDimension(R.dimen.one_dp));
        layoutManager.setAutoMeasureEnabled(false);

        itemSelectedView.setLayoutManager(layoutManager);

        itemSelectedView.setAdapter(mSelectedItemAdapter);

        hitGetItemList();
    }

    private void hitGetItemList() {


        Call<MenuItems> commentsCall = RestServiceFactory.createService().getItemList(4);
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
        switch (view.getId()) {
            case R.id.minusButton:
                for(int i=0;i<mSelectedItemList.size();i++){
                    if(model.getItemCode() == mSelectedItemList.get(i).getItemCode()){

                    }
                }


                break;
            case R.id.plusButton:

                break;
        }
    }

    @Override
    public void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, Item model, int position) {

    }

    @Override
    public void onShowLastItem() {

    }
}
