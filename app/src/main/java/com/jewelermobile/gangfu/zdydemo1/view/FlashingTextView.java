package com.jewelermobile.gangfu.zdydemo1.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class FlashingTextView extends TextView {

    //获取控件的宽
    private int mWidth;
    //获取控件的高
    private int mHeight;
    //线性渲染
    private LinearGradient linearGradient;
    //渲染移动的位置
    private Matrix matrix ;
    private int translate ;

    public FlashingTextView(Context context) {
        this(context,null);
    }

    public FlashingTextView(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlashingTextView(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mWidth <= 0) mWidth = getMeasuredWidth();
        Paint paint = getPaint();
        linearGradient=new LinearGradient(0, 0,mWidth,mHeight,
                new int[]{Color.RED,0xffffffff,Color.BLUE},null, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        matrix=new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (matrix!=null){
            translate +=mWidth/5;
            if (translate>2*mWidth){
                translate = -mWidth;
            }
            matrix.setTranslate(translate,0);
            linearGradient.getLocalMatrix(matrix);
            //无限循环执行
            postInvalidateDelayed(100);
        }
    }
}

