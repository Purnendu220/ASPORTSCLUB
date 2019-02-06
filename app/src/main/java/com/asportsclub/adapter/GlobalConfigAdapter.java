package com.asportsclub.adapter;

import android.content.Context;

import com.asportsclub.R;
import com.asportsclub.rest.Response.GlobalVenderDetail;
import com.asportsclub.rest.Response.GlobalVenderDetails;
import com.jaredrummler.materialspinner.MaterialSpinnerBaseAdapter;
import java.util.List;


import android.content.Context;
        import android.support.annotation.LayoutRes;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import java.util.List;

public class GlobalConfigAdapter extends ArrayAdapter<GlobalVenderDetail>{

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<GlobalVenderDetail> items;
    private final int mResource;

    public GlobalConfigAdapter(@NonNull Context context, @LayoutRes int resource,
                              @NonNull List objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView configItemName = (TextView) view.findViewById(R.id.config_item_name);

        GlobalVenderDetail configItem = items.get(position);
        configItemName.setText(configItem.getVenderName());


        return view;
    }
    public GlobalVenderDetail getItem(int position){
        return items.get(position);
    }
}