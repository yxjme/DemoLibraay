package com.jewelermobile.gangfu.zdydemo1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jewelermobile.gangfu.zdydemo1.R;


/**
 * 二阶贝塞尔曲线
 */
public class BSE2View extends View {


    /*起点*/
    int startPointX;
    int startPointY;

    /*控制点*/
    int conPointX ;
    int conPointY ;

    /*终点*/
    int endPiontX;
    int endPiontY;

    /*曲线*/
    Path path;
    /*画笔*/
    Paint paint;



    public BSE2View(Context context) {
        this(context,null);
    }

    public BSE2View(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BSE2View(Context context,  AttributeSet attrs, int defStyleAttr) {
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
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 开始点的坐标
        startPointX = 0;
        startPointY = 0;
        // 结束点的位置
        endPiontX = w ;
        endPiontY = 0;
        // 控制点坐标
        conPointX = w / 2;
        conPointY = 0;

    }

    float y = 0;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                y = event.getRawY();
            case MotionEvent.ACTION_MOVE:
                conPointX = (int) event.getRawX();
                conPointY = (int) (event.getRawY()-y);
                if (conPointY>0){
                    invalidate();
                }
            break;
            case MotionEvent.ACTION_UP:
                conPointX = endPiontX / 2;
                conPointY = 0;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset(); // 初始化Path
        path.moveTo(startPointX,startPointY);
        path.quadTo(conPointX,conPointY,endPiontX,endPiontY);
        canvas.drawPath(path,paint);
    }
}
