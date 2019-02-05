package com.asportsclub.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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

    public void bind(final TableBookingModel model, final AdapterCallbacks adapterCallbacks, final int position) {
        if(model.isTableStatus()==0){
            textViewTable.setBackgroundColor(context.getResources().getColor(R.color.app_green));
        }
        else if(model.isTableStatus()==1){
            textViewTable.setBackgroundColor(context.getResources().getColor(R.color.hint_color));
        }
        else if(model.isTableStatus()==2){
            textViewTable.setBackgroundColor(context.getResources().getColor(R.color.table_booked));
        }
        textViewTable.setText(model.getTableName());

        textViewTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterCallbacks.onAdapterItemClick(TableBookingViewHolder.this,textViewTable,model,position);
            }
        });


    }
}
