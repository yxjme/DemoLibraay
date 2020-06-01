package com.jewelermobile.gangfu.zdydemo1.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jewelermobile.gangfu.zdydemo1.R;

public class CommonViewHolder extends RecyclerView.ViewHolder {

    public TextView title_textview;
    public TextView text1;
    public CommonViewHolder(@NonNull View itemView) {
        super(itemView);
        text1 = itemView.findViewById(android.R.id.text1);
        title_textview = itemView.findViewById(R.id.title_textview);
    }
}
