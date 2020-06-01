package com.jewelermobile.gangfu.zdydemo1.view.shader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.jewelermobile.gangfu.zdydemo1.R;

public class ShadowLayerView extends View {

    /**阴影*/
    private int elevation;
    /**阴影的颜色*/
    private int elevationColor;
    /**view的形状*/
    private int shape = 1;
    private static final int shapeRect = 1 ;
    private static final int circle = 2 ;
    private static final int round = 3 ;
    /**圆角 x轴半径*/
    private  int roundRx ;
    /**圆角 y轴半径*/
    private  int roundRy ;


    public ShadowLayerView(Context context) {
        this(context,null);
    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }


    public ShadowLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.ShadowLayerView,0,defStyleAttr);

        int count = array.getIndexCount();

        for (int i = 0 ; i<count ; i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.ShadowLayerView_elevate:
                    elevation = array.getInt(attr,20);
                    break;
                case R.styleable.ShadowLayerView_elevationColor:
                    elevationColor = array.getColor(attr,Color.RED);
                    break;
                case R.styleable.ShadowLayerView_shape:
                    shape = array.getInt(attr,shapeRect);
                    break;
                case R.styleable.ShadowLayerView_roundRx:
                    roundRx = array.getInt(attr,0);
                    break;
                case R.styleable.ShadowLayerView_roundRy:
                    roundRy = array.getInt(attr,0);
                    break;
            }
        }
        array.recycle();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(elevation,0,0,elevationColor);
        Rect rect=new Rect(getPaddingLeft(),getPaddingTop(),getWidth()-getPaddingRight(),getBottom()-getPaddingBottom());
        switch (shape){
            case shapeRect:
                canvas.drawRect(rect,paint);
                break;
            case circle:
                int r= Math.min(getWidth(),getHeight())/2;
                canvas.drawCircle(r+getPaddingLeft(),r+getPaddingTop(),r-getPaddingLeft()-getPaddingRight(),paint);
                break;
            case round:
                canvas.drawRoundRect(rect.left,rect.top,rect.right,rect.bottom,roundRx,roundRy,paint);
                break;
        }
    }


    /**
     * @param val
     * @return
     */
    public int dpToPx(int val){
        float density = getResources().getDisplayMetrics().density;
        return (int) (density * val + 0.5f);
    }

}
