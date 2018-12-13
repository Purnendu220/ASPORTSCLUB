package com.asportsclub.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by MyU10 on 3/10/2018.
 */

public class EmptyViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private View mItemView;

    public EmptyViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        mItemView=itemView;
        }

    public void bind() {
        mItemView.setVisibility(View.GONE);
    }
}
