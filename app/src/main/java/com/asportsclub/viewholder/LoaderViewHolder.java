package com.asportsclub.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.asportsclub.ListLoader;
import com.asportsclub.R;
import com.asportsclub.utils.AdapterCallbacks;


/**
 * Created by MyU10 on 3/10/2018.
 */

public class LoaderViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar progressBar;
    public TextView text;

    private Context context;

    public LoaderViewHolder(View view) {
        super(view);

        context = view.getContext();
        progressBar=view.findViewById(R.id.progressBar);
        text=view.findViewById(R.id.text);

    }

    public void bind(ListLoader model, final AdapterCallbacks adapterCallbacks) {

        if (model.isFinish()) {
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }

        if (model.isShowText()) {

            text.setVisibility(View.VISIBLE);

            if (model.isFinish())
                text.setText(model.getFinishText());
            else
                text.setText(model.getLoadingText());

        } else {
            text.setVisibility(View.GONE);
            text.setText("");
        }
    }
}
