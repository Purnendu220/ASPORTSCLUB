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
import com.asportsclub.rest.Response.MenuItem;
import com.asportsclub.rest.Response.SubMenuItem;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.viewholder.EmptyViewHolder;
import com.asportsclub.viewholder.LoaderViewHolder;
import com.asportsclub.viewholder.MenuItemViewHolder;
import com.asportsclub.viewholder.SubMenuItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SubMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private static final int VIEW_TYPE_UNKNOWN = -1;
    private static final int VIEW_TYPE_ITEM = 1;
    private static final int VIEW_TYPE_LOADER = 2;

    private final AdapterCallbacks<Item> adapterCallbacks;

    private List<Object> list;
    private Context context;


    private boolean showLoader;
    private ListLoader listLoader;

    public SubMenuAdapter(Context context, boolean showLoader, AdapterCallbacks<Item> adapterCallbacks) {
        this.adapterCallbacks = adapterCallbacks;
        this.context = context;
        list = new ArrayList<>();
        this.showLoader = showLoader;

        listLoader = new ListLoader(true, "No more tables");
    }

    public List<Object> getList() {
        return list;
    }

    public void addItem(SubMenuItem model) {
        list.add(model);
        addLoader();
        notifyDataSetChanged();
    }

    public void addAllItem(List<SubMenuItem> models) {
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
        if (item instanceof SubMenuItem) {
            itemViewType = VIEW_TYPE_ITEM;
        } else if (item instanceof ListLoader) {
            itemViewType = VIEW_TYPE_LOADER;
        }

        return itemViewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item_view, parent, false);
            return new SubMenuItemViewHolder(view);

        } else if (viewType == VIEW_TYPE_LOADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_progress, parent, false);
            return new LoaderViewHolder(view);
        }
        return new EmptyViewHolder(new LinearLayout(context));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof SubMenuItemViewHolder) {
            ((SubMenuItemViewHolder) holder).bind((SubMenuItem) getItem(position), adapterCallbacks, position);
        } else if (holder instanceof LoaderViewHolder) {
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






