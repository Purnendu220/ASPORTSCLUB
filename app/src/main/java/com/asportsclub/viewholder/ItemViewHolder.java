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




public class ItemViewHolder extends RecyclerView.ViewHolder {

    LinearLayout itemDesc;
    TextView textItemName,textItemRate,textItemQuantity;
    ImageView minusButton,plusButton;
    View plusButtonView,minusButtonView;
    private Context context;


    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();

        itemDesc = (LinearLayout)itemView.findViewById(R.id.itemDesc);
        textItemName = (TextView)itemView.findViewById(R.id.textItemName);
        textItemRate = (TextView)itemView.findViewById(R.id.textItemRate);
        textItemQuantity = (TextView)itemView.findViewById(R.id.textItemQuantity);

        minusButton=(ImageView)itemView.findViewById(R.id.minusButton);
        plusButton=(ImageView)itemView.findViewById(R.id.plusButton);

        plusButtonView=(View)itemView.findViewById(R.id.plusButtonView);
        minusButtonView=(View)itemView.findViewById(R.id.minusButtonView);

        RefrenceWrapper.getRefrenceWrapper(context).getFontTypeFace().setRobotoBoldTypeFace(context,textItemQuantity);
        RefrenceWrapper.getRefrenceWrapper(context).getFontTypeFace().setRobotoItalicTypeFace(context,textItemName,textItemRate);


    }

    public void bind(final Item model, final AdapterCallbacks adapterCallbacks, final int position) {
        textItemName.setText(model.getItemName());
        textItemRate.setText("Rs "+model.getItemRate());
        if(model.getItemQuantity()>0){
            textItemQuantity.setText(model.getItemQuantity()+" ");
            minusButton.setVisibility(View.VISIBLE);
            minusButtonView.setVisibility(View.VISIBLE);
        }else{
            textItemQuantity.setText("ADD");
            minusButton.setVisibility(View.GONE);
            minusButtonView.setVisibility(View.GONE);


        }

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapterCallbacks.onAdapterItemClick(ItemViewHolder.this, minusButton, model, position);

            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapterCallbacks.onAdapterItemClick(ItemViewHolder.this, plusButton, model, position);

            }
        });



    }
}



