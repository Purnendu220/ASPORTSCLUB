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
import com.asportsclub.RefrenceWrapper;
import com.asportsclub.adapter.SubMenuAdapter;
import com.asportsclub.rest.Response.MembershipDetails;
import com.asportsclub.rest.Response.MenuItem;
import com.asportsclub.utils.AdapterCallbacks;
import com.asportsclub.utils.AdapterUpdateListener;

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
        RefrenceWrapper.getRefrenceWrapper(context).getFontTypeFace().setRobotoBoldTypeFace(context,textView);

    }

    public void bind(final MenuItem model, final AdapterCallbacks adapterCallbacks, final int position, final AdapterUpdateListener adapterUpdateListener, MembershipDetails membershipDetails) {
        textView.setText(model.getMenuName());
        imageLeft.setImageDrawable(context.getResources().getDrawable(R.drawable.menu));
        subMenuItems.setLayoutManager(new LinearLayoutManager(context));
        subMenuItems.setHasFixedSize(false);
        submenuItemAdapter = new SubMenuAdapter(context,false,adapterCallbacks,membershipDetails);
        subMenuItems.setAdapter(submenuItemAdapter);
        subMenuItems.setItemAnimator(new DefaultItemAnimator());
        submenuItemAdapter.addAllItem(model.getSubMenuItems());
        if(model.isExpanded()){
            subMenuItems.setVisibility(View.VISIBLE);
            imageRight.setImageResource(R.drawable.list_arrow_down);



        }
        else{
            subMenuItems.setVisibility(View.GONE);
            imageRight.setImageResource(R.drawable.list_arrow_up);
        }


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuItem item = model;
                if(item.isExpanded()){
                    subMenuItems.setVisibility(View.GONE);
                    imageRight.setImageResource(R.drawable.list_arrow_up);
                    item.setExpanded(false);
                }
                else{
                    subMenuItems.setVisibility(View.VISIBLE);
                    imageRight.setImageResource(R.drawable.list_arrow_down);
                    item.setExpanded(true);
                }
                adapterUpdateListener.onAdapterUpdate(item,position);
            }
        });



    }
}



