package com.asportsclub.viewholder;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.asportsclub.ListLoader;
import com.asportsclub.R;
import com.asportsclub.TableBookingModel;
import com.asportsclub.utils.AdapterCallbacks;

public class TableBookingViewHolder extends RecyclerView.ViewHolder {
   TextView textViewTable;
    private Context context;


    public TableBookingViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();

        textViewTable =(TextView)itemView.findViewById(R.id.textViewTable);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void bind(final TableBookingModel model, final AdapterCallbacks adapterCallbacks, final int position) {
        if(model.isTableStatus()){
            textViewTable.setBackgroundColor(context.getColor(R.color.app_green));
        }else{
            textViewTable.setBackgroundColor(context.getColor(R.color.table_booked));

        }

        textViewTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterCallbacks.onAdapterItemClick(TableBookingViewHolder.this,textViewTable,model,position);
            }
        });


    }
}
