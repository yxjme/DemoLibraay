package com.jewelermobile.gangfu.zdydemo1.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

public class MyItem  extends RecyclerView.ItemDecoration {

    //系统的主题
    private static final int[] ATTRS = new int[] { android.R.attr.listDivider };
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private Drawable drawable ;
    private int orientation;
    Context context;


    /**
     * @param drawable
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }



    /**
     * @param context
     * @param orientation
     */
    public MyItem(Context context,int orientation){
        this.orientation=orientation;
        this. context = context ;
        TypedArray array=context.obtainStyledAttributes(ATTRS);
        drawable  = array.getDrawable(0);
        array.recycle();
    }


    /**
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (orientation==VERTICAL){
            drawVertical(c,parent);
        }else if (orientation==HORIZONTAL){
            drawHorizontal(c,parent);
        }
    }

    /**
     * @param c
     * @param parent
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        c.save();
        int count = parent.getChildCount();
        for (int i = 0 ; i < count ;  i++){
            View child = parent.getChildAt(i);
            int top = parent.getPaddingTop();
            int bottom = child.getBottom();
            int left = child.getRight();
            int right = left+drawable.getIntrinsicWidth();
            drawable.setBounds(left,top,right,bottom);
            drawable.draw(c);
        }
        c.restore();
    }


    /**
     * @param c
     * @param parent
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        c.save();
        int count = parent.getChildCount();
        int left = 0;
        int right = parent.getWidth();
        for (int i = 0 ; i < count ;  i++){
            View child = parent.getChildAt(i);
            int top = child.getTop();
            int bottom = child.getBottom()+drawable.getIntrinsicHeight();
            drawable.setBounds(left,top,right,bottom);
            drawable.draw(c);
        }
        c.restore();
    }




    @Override
    public void getItemOffsets(@NonNull Rect outRect, int itemPosition, @NonNull RecyclerView parent) {
        super.getItemOffsets(outRect, itemPosition, parent);
        outRect.set(0,200,0,0);
        if (drawable==null){
            outRect.set(0,0,0,0);
        }else {
            if (orientation==VERTICAL){
                outRect.set(0,0,0,drawable.getIntrinsicHeight());
            }else if (orientation==HORIZONTAL){
                outRect.set(0,0,drawable.getIntrinsicWidth(),0);
            }
        }
    }




}
