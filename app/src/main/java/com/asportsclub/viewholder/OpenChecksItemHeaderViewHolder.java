package com.asportsclub.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.asportsclub.R;
import com.asportsclub.RefrenceWrapper;
import com.asportsclub.rest.Response.ItemHeaderModel;
import com.asportsclub.rest.Response.OpenChecksHeaderModel;
import com.asportsclub.utils.AdapterCallbacks;

public class OpenChecksItemHeaderViewHolder extends RecyclerView.ViewHolder {

    TextView txtBillNo,txtmembershipID,txtTableNo,txtMemberName,txtItemFinalPrice;

    private Context context;


    public OpenChecksItemHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();

        txtBillNo = (TextView)itemView.findViewById(R.id.txtBillNo);
        txtmembershipID = (TextView)itemView.findViewById(R.id.txtmembershipID);
        txtTableNo = (TextView)itemView.findViewById(R.id.txtTableNo);
        txtMemberName = (TextView)itemView.findViewById(R.id.txtMemberName);
        txtItemFinalPrice = (TextView)itemView.findViewById(R.id.txtItemFinalPrice);
        RefrenceWrapper.getRefrenceWrapper(context).getFontTypeFace().setRobotoBoldTypeFace(context,txtBillNo,txtMemberName,txtmembershipID,txtItemFinalPrice,txtTableNo);



    }

    public void bind(final OpenChecksHeaderModel model, final AdapterCallbacks adapterCallbacks, final int position) {

        txtMemberName.setText(model.getMemberName());
        txtTableNo.setText(model.getTableName());
        txtmembershipID.setText(model.getMembershipId());
        txtBillNo.setText(model.getBillNumber());
        txtItemFinalPrice.setText(model.getBillAmount());







    }
}



