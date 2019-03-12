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
import com.asportsclub.rest.Response.ItemHeaderModel;
import com.asportsclub.rest.Response.OpenBillDetail;
import com.asportsclub.rest.Response.OpenBillDetails;
import com.asportsclub.rest.Response.OpenChecksHeaderModel;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.viewholder.EmptyViewHolder;
import com.asportsclub.viewholder.LoaderViewHolder;
import com.asportsclub.viewholder.OpenChecksItemHeaderViewHolder;
import com.asportsclub.viewholder.OpenChecksItemViewHolder;
import com.asportsclub.viewholder.SelectedItemHeaderViewHolder;
import com.asportsclub.viewholder.SelectedItemViewHolder;

import java.util.ArrayList;
import java.util.List;


public class OpenChecksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private static final int VIEW_TYPE_UNKNOWN = -1;
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_LOADER = 2;
    private static final int VIEW_TYPE_HEADER = 3;


    private final AdapterCallbacks<OpenBillDetail> adapterCallbacks;

    private List<Object> list;
    private Context context;


    private boolean showLoader;
    private ListLoader listLoader;

    public OpenChecksAdapter(Context context, boolean showLoader, AdapterCallbacks<OpenBillDetail> adapterCallbacks) {
        this.adapterCallbacks = adapterCallbacks;
        this.context = context;
        list = new ArrayList<>();
        this.showLoader = showLoader;

        listLoader = new ListLoader(true, "No more Items");
    }

    public List<Object> getList() {
        return list;
    }

    public void addItem(OpenBillDetail model) {
        list.add(model);
        addLoader();
        notifyDataSetChanged();
    }

    public void addAllItem(List<OpenBillDetail> models) {
        list.addAll(models);
        addLoader();
        notifyDataSetChanged();

    }
    public void removeItem(int position) {
        try{
            list.remove(position);
            notifyDataSetChanged();

        }catch(Exception e){

        }
    }
    public void addHeader(OpenChecksHeaderModel model){
        list.add(model);
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
        if (item instanceof OpenBillDetail) {
            itemViewType = VIEW_TYPE_ITEM;
        }
        else if (item instanceof OpenChecksHeaderModel){
            itemViewType =  VIEW_TYPE_HEADER;
        }
        else if (item instanceof ListLoader) {
            itemViewType = VIEW_TYPE_LOADER;
        }

        return itemViewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_item_view, parent, false);
            return new OpenChecksItemViewHolder(view);

        }
        if (viewType == VIEW_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_item_header_view, parent, false);
            return new OpenChecksItemHeaderViewHolder(view);

        }
        else if (viewType == VIEW_TYPE_LOADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_progress, parent, false);
            return new LoaderViewHolder(view);
        }
        return new EmptyViewHolder(new LinearLayout(context));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OpenChecksItemViewHolder) {
            ((OpenChecksItemViewHolder) holder).bind((OpenBillDetail) getItem(position), adapterCallbacks, position);
        }
        else if (holder instanceof OpenChecksItemHeaderViewHolder) {
            ((OpenChecksItemHeaderViewHolder) holder).bind((OpenChecksHeaderModel) getItem(position), adapterCallbacks, position);
        }
        else if (holder instanceof LoaderViewHolder) {
            ((LoaderViewHolder) holder).bind(listLoader, adapterCallbacks);
            if (position == getItemCount() - 1 && !listLoader.isFinish()) {
                adapterCallbacks.onShowLastItem();
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



}







