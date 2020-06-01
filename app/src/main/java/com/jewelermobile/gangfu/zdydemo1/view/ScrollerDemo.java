package com.jewelermobile.gangfu.zdydemo1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;



public class ScrollerDemo extends ViewGroup {

    VelocityTracker velocityTracker;
    private Scroller scroller;

    public ScrollerDemo(Context context) {
        this(context,null);
    }

    public ScrollerDemo(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public ScrollerDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount=getChildCount();
        int h = 0;
        int w = 0;
        for (int i = 0 ;i<childCount ;  i++){
            View view = getChildAt(i);
            measureChild(view,widthMeasureSpec,heightMeasureSpec);
            h =  Math.max(h,view.getMeasuredHeight());
            w+=view.getMeasuredWidth();
        }
        setMeasuredDimension(w,h);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取所有子view的数量
        int childCount=getChildCount();
        int left = 0;
        for (int i = 0 ;i<childCount ;  i++){
            View view = getChildAt(i);
            view.layout(left,0,left+view.getMeasuredWidth(),view.getMeasuredHeight());
            left += view.getMeasuredWidth();
        }
    }


    float lastMoveX = 0;
    float currentMoveX;
    float downX = 0;
    float totalMoveX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                velocityTracker  = VelocityTracker.obtain();
                downX =event.getRawX();
            case MotionEvent.ACTION_MOVE:
                if (!scroller.isFinished()) scroller.abortAnimation();
                velocityTracker.addMovement(event);


                currentMoveX = event.getRawX() - downX + lastMoveX;

                //左边界
                if (currentMoveX >= 0) {
                    currentMoveX = 0 ;
                }

                if (getWidth()<getResources().getDisplayMetrics().widthPixels){
                    currentMoveX = 0 ;
                }


                //右边界
                if (Math.abs(currentMoveX) >= (getWidth()-getResources().getDisplayMetrics().widthPixels)){
                    currentMoveX = -(getWidth()-getResources().getDisplayMetrics().widthPixels);
                }


                scrollTo((int) -currentMoveX,0);

                Log.v("=======s======",""+currentMoveX);
                velocityTracker.computeCurrentVelocity(1000,ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity());

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                lastMoveX =  currentMoveX;
//                int dx = (getWidth() / getChildCount())*getPosition();
//                scrollTo(dx,0);
//
//                scroller.startScroll( getScrollX(),0, dx,0);
//                lastMoveX =  dx;
//                invalidate();

                Log.v("=======tyTracker======",""+velocityTracker.getXVelocity());
//                ViewConfiguration configuration=ViewConfiguration.get(getContext());
//                configuration.getScaledTouchSlop();
//                configuration.getScaledMaximumFlingVelocity();
//                configuration.getScaledMinimumFlingVelocity();
//                scroller.fling(getScrollX(),0,configuration.getScaledMaximumFlingVelocity(),0,);
                scroller.fling(getScrollX(),0,(int)velocityTracker.getXVelocity(),0,0,(getWidth()-getScrollX()),0,0);
                velocityTracker.clear();
                velocityTracker.recycle();
                velocityTracker = null ;
                break;

        }
        return true;
    }





    /**
     * 计算当前索引
     */
    public int getPosition(){
        float scale = Math.abs(currentMoveX/getWidth());
        int position = Math.round(scale*getChildCount());
        return position;
    }




    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }
}
