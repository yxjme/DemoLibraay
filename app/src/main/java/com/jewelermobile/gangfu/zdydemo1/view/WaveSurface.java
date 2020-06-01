package com.jewelermobile.gangfu.zdydemo1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaveSurface extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    private SurfaceHolder holder ;

    private Paint paint;

    private Canvas canvas ;
    /***
     * 是否在绘制:用于关闭子线程:true则表示一直循环
     */
    private boolean isDrawing = true;

    private Context context;

    public WaveSurface(Context context) {
        this(context,null);
    }

    public WaveSurface(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public WaveSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        holder = getHolder();
        holder.addCallback(this);
        initPaint();

    }

    private void initPaint() {
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        new Thread(WaveSurface.this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false ;
    }


    @Override
    public void run() {
        while (isDrawing){
            drawView();
        }
    }


    private int speed;
    private int s = 5;
    private int b = 2  ;
    private List<Bean> list=new ArrayList<>();
    private void drawView() {
        canvas = holder.lockCanvas() ;
        canvas.drawColor(Color.WHITE);
        /**将需要绘制的线 存入数据集合中*/
        list.add(new Bean(speed+b,speed+=s+b));


        tX+=s+b ;

        if (speed>w){
            canvas.translate(w-tX,0);
            canvas.drawLine(0,150,speed,150,paint);
        }else {
            canvas.drawLine(0,150,w,150,paint);
        }


        Random random=new Random(300);
        for (Bean b:list){
            int h= random.nextInt(300);
            canvas.drawLine(b.x1,(300-h)/2,b.x2,(300-h)/2+h,paint);
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (null != canvas){
            holder.unlockCanvasAndPost(canvas);
        }
    }


    int tX;
    int w = getContext().getResources().getDisplayMetrics().widthPixels;
    class Bean{
        int x1;
        int x2;
        public Bean(int x1, int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }
    }
}
