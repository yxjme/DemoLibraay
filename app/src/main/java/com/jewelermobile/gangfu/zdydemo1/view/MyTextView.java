package com.jewelermobile.gangfu.zdydemo1.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.opengl.Matrix;
import android.os.Build;
import android.print.PrintAttributes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

/**
 * Created by zbsdata on 2017/3/10.
 */

public class MyTextView extends TextView {

//    1、自定义View的属性
//    2、在View的构造方法中获得我们自定义的属性
//    3、重写onMesure
//    4、重写onDraw


//    EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
//    AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
//    UNSPECIFIED：表示子布局想要多大就多大，很少使用



    private String text;
    private int textColor;
    private int textSize;


    public float startPull=0;
    public float endPull=0;
    public float pullSize=0;

    public Boolean isPull=false;
    public int pullDown=0;


    public MyTextView(Context context) {
        this(context,null);
    }
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if(changed){
            top= (int) pullSize;
            Log.v("======下拉的距离=",String.valueOf(pullSize));
        }
        super.onLayout(changed, left, top, right, bottom);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**/
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        /*空间的高*/
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        Log.v("======下拉的距离=",String.valueOf(pullSize));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.rotate(-45);
    }


    Boolean isRotation=false;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction()){

            case MotionEvent.ACTION_DOWN:
                startPull= (int) motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endPull=motionEvent.getY();
                pullSize=endPull-startPull;
                if(pullSize>0){
                    isPull=true;
                }else {
                    isPull=false;
                }
                Log.v("======下拉的距离=",String.valueOf(pullSize/2));
                float moveY = pullSize / 2;
                this.layout(0, (int) moveY ,getWidth(),getResources().getDisplayMetrics().heightPixels);
                if(moveY>100){
                    setText("松开即可刷新");
                    if(!isRotation){
//                        ObjectAnimator anim=ObjectAnimator.ofFloat(this,"rotationX",0,180);
//                        anim.setRepeatCount(1);
//                        anim .setDuration(500);
//                        anim.start();
                    }
                }else {
                    setText("下拉刷新");
//                    ObjectAnimator anim=ObjectAnimator.ofFloat(this,"rotationX",180,0);
//                    anim.setRepeatCount(1);
//                    anim .setDuration(500);
//                    anim.start();
                }
                break;


            /*放开之后做相应的操作*/
            case MotionEvent.ACTION_UP:
//                this.setMeasuredDimension(getWidth(), (int) (getHeight()-pullSize));
                this.layout(0,0,getWidth(),getResources().getDisplayMetrics().heightPixels);
                isRotation=true;
                break;
        }
        return true;
    }

}


