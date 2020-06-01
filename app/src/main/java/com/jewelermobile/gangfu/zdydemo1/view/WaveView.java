package com.jewelermobile.gangfu.zdydemo1.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class WaveView extends View {

    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(50*20+50*10,300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        for (int i=0;i<50;i++){
            canvas.drawLine(speed+s,0,speed+=10+s,300,paint);
        }
    }

    private int speed=10;
    private int s = 20;
}
