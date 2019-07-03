package com.jewelermobile.gangfu.zdydemo1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

public class MyItem1 extends RecyclerView.ItemDecoration {


    Context context;
    public MyItem1(Context context){
        this.context=context;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //获取到视图中第一个可见的item的position
        View view = LayoutInflater.from(context).inflate(R.layout.vloat_layout,null);
        view.layout(0,0,parent.getWidth(),200);
        c.save();
        view.draw(c);
        c.restore();
    }
}
