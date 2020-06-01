package com.jewelermobile.gangfu.zdydemo1.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class FloatImageView extends ImageView implements View.OnTouchListener , ViewTreeObserver.OnGlobalLayoutListener {

    /*当前在屏幕上按下的Y轴坐标*/
    private int downY;
    /*上一次 移动y*/
    private int lastMoveY;
    /*当前y移动的距离*/
    private int currentMoveY = 0;
    private int parentWidth = getResources().getDisplayMetrics().widthPixels;
    private int parentHeight = getResources().getDisplayMetrics().heightPixels;
    /*用于判断 移动的最小距离*/
    private int dy;
    private int downDy;


    /**
     * 获取父级view的宽高
     *
     * @param parentView
     */
    public void setParentView(View parentView) {
        parentWidth = parentView.getMeasuredWidth();
        parentHeight = parentView.getMeasuredHeight();
    }


    public FloatImageView(Context context) {
        this(context,null);
    }


    public FloatImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }


    public FloatImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
        point = new Point();
    }

    private Point point ;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float y = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downDy = (int) y;
                downY = (int) y;
                return true;
            case MotionEvent.ACTION_MOVE:
                dy = (int) Math.abs(y - downDy );
                currentMoveY = (int) (y - downY + lastMoveY);
                layout(parentWidth - getWidth() , currentMoveY , parentWidth,currentMoveY+getHeight());
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (getTop()<0){
                    currentMoveY = 0 ;
                    layout(
                            parentWidth - getWidth() ,
                            0,
                            parentWidth,
                            currentMoveY+getHeight());
                }

                if (getBottom()>parentHeight){
                    currentMoveY = parentHeight - getHeight();
                    layout(parentWidth - getWidth() , parentHeight -getHeight() , parentWidth, parentHeight);
                }

                lastMoveY = currentMoveY;
                point.y = currentMoveY ;

                if (dy < ViewConfiguration.getTouchSlop()){
                    if (onClickListener!=null)
                        onClickListener.onClick(this);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + event.getAction());
        }
        return false;
    }

    @Override
    public void onGlobalLayout() {
        lastMoveY = getTop();
        point.x = parentWidth - getWidth();
        point.y = getTop();
    }

    private OnClickListener onClickListener ;

    public void setMyOnClickListener(OnClickListener l) {
        this.onClickListener = l;
    }
}
