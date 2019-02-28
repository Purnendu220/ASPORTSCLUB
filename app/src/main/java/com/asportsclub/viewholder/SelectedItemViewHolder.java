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
import com.asportsclub.utils.AdapterCallbacks;

import java.text.DecimalFormat;


public class SelectedItemViewHolder extends RecyclerView.ViewHolder {

    TextView textItemName,textItemRate,textItemQuantity,txtItemGst,txtItemFinalPrice;

    private Context context;


    public SelectedItemViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();


        textItemName = (TextView)itemView.findViewById(R.id.txtItemName);
        textItemRate = (TextView)itemView.findViewById(R.id.txtItemPrice);
        textItemQuantity = (TextView)itemView.findViewById(R.id.txtItemQuantity);
        txtItemGst = (TextView)itemView.findViewById(R.id.txtItemGst);
        txtItemFinalPrice = (TextView)itemView.findViewById(R.id.txtItemFinalPrice);
        RefrenceWrapper.getRefrenceWrapper(context).getFontTypeFace().setRobotoMediumTypeFace(context,textItemName,textItemQuantity,textItemRate,txtItemFinalPrice,txtItemGst);

    }

    public void bind(final Item model, final AdapterCallbacks adapterCallbacks, final int position) {

        textItemName.setText(model.getItemName());
        textItemRate.setText(model.getItemRate()+"");
        textItemQuantity.setText(model.getItemQuantity()+"");
        txtItemGst.setText(model.getTaxPercentage()+"");

        double finalprice = (((model.getItemRate() * model.getItemQuantity())*model.getServiceCharge())/100)+(((model.getItemRate() * model.getItemQuantity())*model.getTaxPercentage())/100) + (model.getItemRate() * model.getItemQuantity());


        txtItemFinalPrice.setText(new DecimalFormat("##.##").format(finalprice)+"");






    }
}



