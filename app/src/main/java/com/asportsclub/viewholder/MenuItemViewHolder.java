package com.asportsclub.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.asportsclub.R;
import com.asportsclub.adapter.SubMenuAdapter;
import com.asportsclub.rest.Response.MenuItem;
import com.asportsclub.utils.AdapterCallbacks;

public class MenuItemViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    ImageView imageLeft,imageRight;
    RecyclerView subMenuItems;
    private Context context;
    private SubMenuAdapter submenuItemAdapter;


    public MenuItemViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();

        textView =(TextView)itemView.findViewById(R.id.textView);
        imageLeft=(ImageView)itemView.findViewById(R.id.imageLeft);
        imageRight=(ImageView)itemView.findViewById(R.id.imageRight);
        subMenuItems=(RecyclerView)itemView.findViewById(R.id.subMenuItems);

    }

    public void bind(final MenuItem model, final AdapterCallbacks adapterCallbacks, final int position) {
        textView.setText(model.getMenuName());
        imageLeft.setImageDrawable(context.getResources().getDrawable(R.drawable.menu));
        subMenuItems.setVisibility(View.VISIBLE);
        subMenuItems.setLayoutManager(new LinearLayoutManager(context));
        subMenuItems.setHasFixedSize(false);
        submenuItemAdapter = new SubMenuAdapter(context,false,adapterCallbacks);
        subMenuItems.setAdapter(submenuItemAdapter);
        subMenuItems.setItemAnimator(new DefaultItemAnimator());
        submenuItemAdapter.addAllItem(model.getSubMenuItems());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subMenuItems.getVisibility() == View.VISIBLE){
                    subMenuItems.setVisibility(View.GONE);
                    imageRight.setImageResource(R.drawable.list_arrow_up);


                }else{
                    subMenuItems.setVisibility(View.VISIBLE);
                    imageRight.setImageResource(R.drawable.list_arrow_down);

                }

            }
        });



    }
}



