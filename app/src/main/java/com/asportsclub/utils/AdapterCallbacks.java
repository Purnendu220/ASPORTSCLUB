package com.asportsclub.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface AdapterCallbacks<Object> {
    void onAdapterItemClick(RecyclerView.ViewHolder viewHolder, View view, Object model, int position);

    void onAdapterItemLongClick(RecyclerView.ViewHolder viewHolder, View view, Object model, int position);

    void onShowLastItem();
}
