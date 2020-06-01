package com.jewelermobile.gangfu.zdydemo1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jewelermobile.gangfu.zdydemo1.R;


/**
 * 三阶贝塞尔曲线
 *
 * 需要两个控制点
 *
 */
public class BSE3View  extends View {

    /*起点*/
    int startPointX;
    int startPointY;

    /*控制点*/
    int con1PointX ;
    int con1PointY ;

    int con2PointX ;
    int con2PointY ;

    /*终点*/
    int endPiontX;
    int endPiontY;

    /*曲线*/
    Path path;
    /*画笔*/
    Paint paint;



    public BSE3View(Context context) {
        this(context,null);
    }

    public BSE3View(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BSE3View(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }



    private void initPaint() {
        path = new Path();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setColor(getContext().getResources().getColor(R.color.colorPrimaryDark));
        paint.setStrokeWidth(10);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
         startPointX = 0 ;
         startPointY = h / 2;

        endPiontX = w  ;
        endPiontY = h / 2;

         con1PointX =  w / 5;
         con1PointY = h /4 ;

         con2PointX =  w * 4 / 5 ;
         con2PointY  = h /4;
    }



    boolean isTowFinger;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_POINTER_DOWN:
                isTowFinger = true ;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                isTowFinger = false ;
                break;
            case MotionEvent.ACTION_MOVE:
                con1PointX = (int) event.getX(0);
                con1PointY = (int) event.getY(0);
                if (isTowFinger){
                    con2PointX = (int) event.getX(1);
                    con2PointY = (int) event.getY(1);
                }
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        path.moveTo(startPointX,startPointY);
        path.cubicTo(con1PointX,con1PointY,con2PointX,con2PointY,endPiontX,endPiontY);
        canvas.drawPath(path,paint);
    }
}
