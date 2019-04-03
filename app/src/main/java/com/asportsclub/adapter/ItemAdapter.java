package com.asportsclub.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.asportsclub.ListLoader;
import com.asportsclub.R;
import com.asportsclub.rest.Response.Item;
import com.asportsclub.rest.Response.MembershipDetails;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.utils.AppContext;
import com.asportsclub.utils.ToastUtils;
import com.asportsclub.viewholder.EmptyViewHolder;
import com.asportsclub.viewholder.ItemViewHolder;
import com.asportsclub.viewholder.LoaderViewHolder;

import java.util.ArrayList;
import java.util.List;



public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterCallbacks {
    private static final int VIEW_TYPE_UNKNOWN = -1;
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_LOADER = 2;

    private final AdapterCallbacks<Item> adapterCallbacks;

    private List<Object> list;
    private Context context;

    private MembershipDetails membershipDetails;
    private boolean showLoader;
    private ListLoader listLoader;

    public ItemAdapter(Context context, boolean showLoader, AdapterCallbacks<Item> adapterCallbacks, MembershipDetails openingBalnce) {
        this.adapterCallbacks = adapterCallbacks;
        this.context = context;
        list = new ArrayList<>();
        this.showLoader = showLoader;
        this.membershipDetails=openingBalnce;
        listLoader = new ListLoader(true, "No more Items");
    }

    public List<Object> getList() {
        return list;
    }

    public void addItem(Item model) {
        list.add(model);
        addLoader();
        notifyDataSetChanged();
    }

    public void addAllItem(List<Item> models) {
        list.addAll(models);
        addLoader();
        notifyDataSetChanged();

    }

    public void clearAll() {
        list.clear();
    }

    public void loaderDone() {
        listLoader.setFinish(true);
        try {
            notifyItemChanged(list.indexOf(listLoader));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loaderReset() {
        listLoader.setFinish(false);
    }

    private void addLoader() {
        if (showLoader) {
            list.remove(listLoader);
            list.add(listLoader);
        }
    }

    @Override
    public int getItemViewType(int position) {

        int itemViewType = VIEW_TYPE_UNKNOWN;

        Object item = getItem(position);
        if (item instanceof Item) {
            itemViewType = VIEW_TYPE_ITEM;
        } else if (item instanceof ListLoader) {
            itemViewType = VIEW_TYPE_LOADER;
        }

        return itemViewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new ItemViewHolder(view);

        } else if (viewType == VIEW_TYPE_LOADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_progress, parent, false);
            return new LoaderViewHolder(view);
        }
        return new EmptyViewHolder(new LinearLayout(context));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).bind((Item) getItem(position), this, position);
        } else if (holder instanceof LoaderViewHolder) {
            ((LoaderViewHolder) holder).bind(listLoader, this);
            if (position == getItemCount() - 1 && !listLoader.isFinish()) {
                this.onShowLastItem();
            }
        } else if (holder instanceof EmptyViewHolder) {
            ((EmptyViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Object getItem(int position) {
        if (list != null && list.size() > 0)
            return list.get(position);
        else
            return null;
    }

    @Override
    public void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, Object model, int position) {
        Item item = null;
        switch (view.getId()) {
            case R.id.minusButton:
                item = (Item) getItem(position);
                if(item.getOrderedQuantity() != item.getItemQuantity()){
                    int itemQuantity=item.getItemQuantity();
                    item.setItemQuantity(itemQuantity-1);
                    notifyItemChanged(position);
                }
                break;
            case R.id.plusButton:
                item = (Item) getItem(position);
                if(membershipDetails.getMemberType().equalsIgnoreCase("D")||membershipDetails.getMemberType().equalsIgnoreCase("X")){
                    if((AppContext.getInstance().getGetItemTotal()+item.getItemRate()) > membershipDetails.getOpeningBalance()){
                        ToastUtils.show(context,"You can't add this item your balance is low.");
                        return;
                    }
                }
                int itemModelQuantity=item.getItemQuantity();
                item.setItemQuantity(itemModelQuantity+1);
                notifyItemChanged(position);
                break;
        }
        adapterCallbacks.onAdapterItemClick(viewHolder,view,item,position);


    }

    @Override
    public void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, Object model, int position) {

    }

    @Override
    public void onShowLastItem() {

    }
}







