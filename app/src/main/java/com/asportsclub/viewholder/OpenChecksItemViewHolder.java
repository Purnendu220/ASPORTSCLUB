package com.asportsclub.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.asportsclub.R;
import com.asportsclub.RefrenceWrapper;
import com.asportsclub.rest.Response.Item;
import com.asportsclub.rest.Response.OpenBillDetail;
import com.asportsclub.utils.AdapterCallbacks;

import java.text.DecimalFormat;


public class OpenChecksItemViewHolder extends RecyclerView.ViewHolder {

    TextView txtBillNo,txtmembershipID,txtTableNo,txtMemberName,txtItemFinalPrice;
    LinearLayout llTop;
    private Context context;


    public OpenChecksItemViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();


        txtBillNo = (TextView)itemView.findViewById(R.id.txtBillNo);
        txtmembershipID = (TextView)itemView.findViewById(R.id.txtmembershipID);
        txtTableNo = (TextView)itemView.findViewById(R.id.txtTableNo);
        txtMemberName = (TextView)itemView.findViewById(R.id.txtMemberName);
        txtItemFinalPrice = (TextView)itemView.findViewById(R.id.txtItemFinalPrice);

        RefrenceWrapper.getRefrenceWrapper(context).getFontTypeFace().setRobotoMediumTypeFace(context,txtBillNo,txtMemberName,txtmembershipID,txtTableNo,txtItemFinalPrice);

    }

    public void bind(final OpenBillDetail model, final AdapterCallbacks adapterCallbacks, final int position) {

        txtMemberName.setText(model.getMemberName());
        txtTableNo.setText(model.getTableName());
        txtmembershipID.setText(model.getMembershipId());
        txtBillNo.setText(model.getBillNumber()+"");



        txtItemFinalPrice.setText(model.getBillAmount()+"");






    }
}



