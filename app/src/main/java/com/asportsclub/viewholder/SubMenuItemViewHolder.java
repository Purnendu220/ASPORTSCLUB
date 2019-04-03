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
import com.asportsclub.adapter.ItemAdapter;
import com.asportsclub.adapter.SubMenuAdapter;
import com.asportsclub.rest.Response.MembershipDetails;
import com.asportsclub.rest.Response.MenuItem;
import com.asportsclub.rest.Response.SubMenuItem;
import com.asportsclub.utils.AdapterCallbacks;

public class SubMenuItemViewHolder  extends RecyclerView.ViewHolder {

    TextView textView;
    ImageView imageLeft,imageRight;
    RecyclerView subMenuItems;
    private Context context;
    ItemAdapter itemAdapter;


    public SubMenuItemViewHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();

        textView =(TextView)itemView.findViewById(R.id.textView);
        imageLeft=(ImageView)itemView.findViewById(R.id.imageLeft);
        imageRight=(ImageView)itemView.findViewById(R.id.imageRight);
        subMenuItems=(RecyclerView)itemView.findViewById(R.id.subMenuItems);
        RefrenceWrapper.getRefrenceWrapper(context).getFontTypeFace().setRobotoBoldTypeFace(context,textView);

    }

    public void bind(final SubMenuItem model, final AdapterCallbacks adapterCallbacks, final int position, MembershipDetails openingBalnce) {
        textView.setText(model.getSubMenuName());
        subMenuItems.setVisibility(View.VISIBLE);
        subMenuItems.setLayoutManager(new LinearLayoutManager(context));
        subMenuItems.setHasFixedSize(false);
        itemAdapter = new ItemAdapter(context,false,adapterCallbacks,openingBalnce);
        subMenuItems.setAdapter(itemAdapter);
        subMenuItems.setItemAnimator(new DefaultItemAnimator());
       // if(model.getItems()!=null)
        itemAdapter.addAllItem(model.getItems());
        imageRight.setImageResource(R.drawable.list_arrow_down);


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
