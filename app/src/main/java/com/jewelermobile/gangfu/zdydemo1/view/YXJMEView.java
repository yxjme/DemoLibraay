package com.jewelermobile.gangfu.zdydemo1.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;


@SuppressLint("AppCompatCustomView")
public class YXJMEView extends TextView {

    private TextView sTextView;

    public YXJMEView(Context context) {
        this(context,null);
    }


    public YXJMEView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public YXJMEView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        sTextView = new TextView(context,attrs,defStyleAttr);
        Paint paint = sTextView.getPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        sTextView.setTextColor(Color.BLUE);
        sTextView.setGravity(getGravity());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        CharSequence str = sTextView.getText();
        if (sTextView!=null||!sTextView.equals(str)){
            sTextView.setText(getText());
            postInvalidate();
        }
        sTextView.measure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        sTextView.setLayoutParams(params);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        sTextView.layout(left,top,right,bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        sTextView.draw(canvas);
        super.onDraw(canvas);
    }
}
