package com.jewelermobile.gangfu.zdydemo1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyWaveView extends SurfaceView implements SurfaceHolder.Callback {


    private boolean isDrawing = true;
    /*绘制的开关*/
    private boolean isSwitch  = true;

    private int speedTime = 150;//mm

    public MyWaveView(Context context) {
        this(context,null);
    }

    public MyWaveView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        SurfaceHolder hol = getHolder();
        hol.addCallback(this);
    }


    public void start(){
        isSwitch = true ;
    }


    public void stop(){
        isSwitch = false ;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread(new MyRunnable(holder)).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false ;
    }


    public class MyRunnable implements Runnable{


        private int a ;
        private SurfaceHolder holder;

        public MyRunnable(SurfaceHolder holder) {
            this.holder=holder;
            initPaint();
        }


        @Override
        public void run() {
            long tempTime = 0;
            while (isDrawing){
                if (isSwitch){
                    if (System.currentTimeMillis() - tempTime>speedTime){
                        drawView();
                        tempTime = System.currentTimeMillis() ;
                    }
                }
            }
        }


        /**
         * 开始绘制
         */
        List<RectF> list = new ArrayList<>();
        private void drawView() {
            Canvas canvas = holder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            a++ ;

            setMeasuredDimension(a*15,getHeight());
            int speed = 0;

            for (int i = 0 ; i < a ; i++ ){
                Random random =  new Random(300);
                canvas.drawLine(speed,0,speed, random.nextInt(300) ,paint);
                speed+=15 ;
                Log.v("============","======getWidth()====="+getWidth()+",speed="+speed);
            }

            /*释放*/
            if (null != canvas){
                holder.unlockCanvasAndPost(canvas);
            }
        }





        /*画笔*/
        private Paint paint ;


        public void initPaint(){
            paint=new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setTextSize(100);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(10);
        }
    }
}
