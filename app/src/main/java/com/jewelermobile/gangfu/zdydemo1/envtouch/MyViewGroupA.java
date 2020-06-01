package com.jewelermobile.gangfu.zdydemo1.envtouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class MyViewGroupA extends ViewGroup {

    public MyViewGroupA(Context context) {
        this(context,null);
    }

    public MyViewGroupA(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(300,300);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        getChildAt(0).layout(0,0,200,200);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.v("=====onClick====","MyViewGroupA-------->onInterceptTouchEvent");
        return true;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.v("=====onClick====","MyViewGroupA-------->dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v("=====onClick====","MyViewGroupA-------->onTouchEvent");
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        Paint paint=new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.BLACK);
        canvas.drawText("A",0,getHeight()-30,paint);
    }
}
