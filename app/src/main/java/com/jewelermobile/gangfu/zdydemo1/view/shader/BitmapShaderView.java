package com.jewelermobile.gangfu.zdydemo1.view.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jewelermobile.gangfu.zdydemo1.R;


public class BitmapShaderView extends View {


    int radius = 300 ;
    public BitmapShaderView(Context context) {
        this(context,null);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (radius>0){
            setMeasuredDimension(radius*2+getPaddingLeft()+getPaddingRight(),radius*2+getPaddingTop()+getPaddingBottom());
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a0);

        /**
         * replicate the edge color if the shader draws outside of its
         * original bounds
         *    CLAMP   (0), 拉伸
         * repeat the shader's image horizontally and vertically
         *    REPEAT  (1),
         * repeat the shader's image horizontally and vertically, alternating
         * mirror images so that adjacent images always seam
         *    IRROR  (2);
         */
        BitmapShader bitmapShader=new BitmapShader(bitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
        paint.setColor(Color.RED);
        /**
         * Blur inside and outside the original border.
            NORMAL(0),
         * Draw solid inside the border, blur outside.
            SOLID(1),
         * Draw nothing inside the border, blur outside.
            OUTER(2),
         * Blur inside the border, draw nothing outside.
            INNER(3);
         */
        paint.setMaskFilter(new BlurMaskFilter(50,BlurMaskFilter.Blur.SOLID));
        canvas.drawCircle(radius+getPaddingLeft(),radius+getPaddingTop(),radius,paint);
    }
}
