package com.asportsclub.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asportsclub.R;
import com.asportsclub.RefrenceWrapper;
import com.asportsclub.rest.Response.Item;
import com.asportsclub.rest.Response.ItemHeaderModel;
import com.asportsclub.utils.AdapterCallbacks;

public class SelectedItemHeaderViewHolder extends RecyclerView.ViewHolder {

    TextView textItemName,textItemRate,textItemQuantity,txtItemGst,txtItemFinalPrice;

    private Context context;


    public SelectedItemHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();

        textItemName = (TextView)itemView.findViewById(R.id.txtItemName);
        textItemRate = (TextView)itemView.findViewById(R.id.txtItemPrice);
        textItemQuantity = (TextView)itemView.findViewById(R.id.txtItemQuantity);
        txtItemGst = (TextView)itemView.findViewById(R.id.txtItemGst);
        txtItemFinalPrice = (TextView)itemView.findViewById(R.id.txtItemFinalPrice);
        RefrenceWrapper.getRefrenceWrapper(context).getFontTypeFace().setRobotoBoldTypeFace(context,textItemName,textItemRate,textItemQuantity,txtItemFinalPrice,txtItemGst);



    }

    public void bind(final ItemHeaderModel model, final AdapterCallbacks adapterCallbacks, final int position) {

        textItemName.setText(model.getItemName());
        textItemRate.setText(model.getItemPrice());
        textItemQuantity.setText(model.getItemQuantity());
        txtItemGst.setText(model.getItemGst());
        txtItemFinalPrice.setText(model.getItemFinalPrice());







    }
}



