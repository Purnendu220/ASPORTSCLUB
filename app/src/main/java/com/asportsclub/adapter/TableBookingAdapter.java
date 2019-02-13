package com.asportsclub.adapter;


import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;
        import com.asportsclub.ListLoader;
import com.asportsclub.R;
import com.asportsclub.TableBookingModel;
import com.asportsclub.rest.Response.VenderTableDetail;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.viewholder.EmptyViewHolder;
import com.asportsclub.viewholder.LoaderViewHolder;
import com.asportsclub.viewholder.TableBookingViewHolder;

import java.util.ArrayList;
        import java.util.List;

public class TableBookingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_UNKNOWN = -1;
    private static final int VIEW_TYPE_CLASS = 1;
    private static final int VIEW_TYPE_LOADER = 2;

    private final AdapterCallbacks<VenderTableDetail> adapterCallbacks;

    private List<VenderTableDetail> list;
    private Context context;


    private boolean showLoader;
    private ListLoader listLoader;

    public TableBookingAdapter(Context context, boolean showLoader, AdapterCallbacks<VenderTableDetail> adapterCallbacks) {
        this.adapterCallbacks = adapterCallbacks;
        this.context = context;
        list = new ArrayList<>();
        this.showLoader = showLoader;

        listLoader = new ListLoader(true, "No more tables");
    }

    public List<VenderTableDetail> getList() {
        return list;
    }

    public void addClass(VenderTableDetail model) {
        list.add(model);
       // addLoader();
    }

    public void addAllTableData(List<VenderTableDetail> models) {
        list.addAll(models);
       // addLoader();
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

        }
    }

    @Override
    public int getItemViewType(int position) {

        int itemViewType = VIEW_TYPE_UNKNOWN;

        Object item = getItem(position);
        if (item instanceof VenderTableDetail) {
            itemViewType = VIEW_TYPE_CLASS;
        } else if (item instanceof ListLoader) {
            itemViewType = VIEW_TYPE_LOADER;
        }

        return itemViewType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_CLASS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_table_booking, parent, false);
            return new TableBookingViewHolder(view);

        } else if (viewType == VIEW_TYPE_LOADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_list_progress, parent, false);
            return new LoaderViewHolder(view);
        }
        return new EmptyViewHolder(new LinearLayout(context));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TableBookingViewHolder) {
            ((TableBookingViewHolder) holder).bind((VenderTableDetail) getItem(position), adapterCallbacks, position);
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