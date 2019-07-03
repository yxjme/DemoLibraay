package com.jewelermobile.gangfu.zdydemo1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jewelermobile.gangfu.zdydemo1.R;

/**
 * Created by zbsdata on 2017/3/10.
 */

public class MyView0 extends View {

    private String text;
    private int textColor;
    private int textSize;


    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound;
    private Paint mPaint;



    public MyView0(Context context) {
        this(context,null);
    }

    public MyView0(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView0(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a=context.getTheme().obtainStyledAttributes(attrs,R.styleable.MyView0,0,defStyleAttr);
        int count=a.getIndexCount();
        for (int i=0;i<count;i++){
            int atte=a.getIndex(i);
            switch (atte){
                case R.styleable.MyView0_text:
                    text=a.getString(atte);
                    break;
                case R.styleable.MyView0_textColor:
                    textColor=a.getColor(atte, Color.BLACK);
                    break;
                case R.styleable.MyView0_textSize:
                    textSize=a.getDimensionPixelSize(atte,15);
                    break;
            }
        }
        a.recycle();
        /**
         * 获得绘制文本的宽和高
         */
        mPaint = new Paint();
        mPaint.setTextSize(textSize);
        // mPaint.setColor(mTitleTextColor);
        mBound = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), mBound);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
          /**/
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        /*空间的高*/
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        int height;
        int width;
        if (widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }else {
            mPaint.setTextSize(textSize);
            mPaint.getTextBounds(text, 0, text.length(), mBound);
            width=mBound.width()+getPaddingLeft()+getPaddingRight();
        }

        if(heightMeasureSpec==MeasureSpec.EXACTLY){
            height=heightSize;
        }else {
            mPaint.setTextSize(textSize);
            mPaint.getTextBounds(text, 0, text.length(), mBound);
            height=mBound.height()+getPaddingTop()+getPaddingBottom();
        }

        /*重新测量当前view的大小*/
        setMeasuredDimension(width,height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeWidth(1);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(textColor);
        canvas.drawText(text,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,mPaint);

        canvas.rotate(-45, this.getWidth()/3f , this.getHeight()*2/3f);
        postInvalidate();
    }
}
