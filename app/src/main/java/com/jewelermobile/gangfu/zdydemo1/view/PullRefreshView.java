package com.jewelermobile.gangfu.zdydemo1.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;

import com.jewelermobile.gangfu.zdydemo1.R;

public class PullRefreshView extends ViewGroup  {

    /*列表*/
    private RecyclerView recyclerView;
    private View headView;
    private TextView tv_title;


    public PullRefreshView(Context context) {
        this(context,null);
    }

    public PullRefreshView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PullRefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
        initHeadView();
    }

    private void initHeadView() {
        headView=LayoutInflater.from(getContext()).inflate(R.layout.pull_start_refresh_layout,null);
        headView.setTag("head");
        tv_title = headView.findViewById(R.id.tv_title);
        addView(headView,0);
    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getWidthMeasureSpec(widthMeasureSpec),getHeightMeasureSpec(heightMeasureSpec));
    }


    /**
     * 获取测量宽度
     * @param widthMeasureSpec
     * @return
     */
    private int getWidthMeasureSpec(int widthMeasureSpec) {
        int w = 0;
        int widthMode =  MeasureSpec.getMode(widthMeasureSpec);
        int widthSize =  MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY){
            w = widthSize ;
        }else {
            if (widthMode==MeasureSpec.AT_MOST){
                w = Math.min(0,widthSize);
            }
        }
        return w ;
    }


    /**
     * 获取测量高度
     * @param heightMeasureSpec
     * @return
     */
    private int getHeightMeasureSpec(int heightMeasureSpec) {
        int h = 0;
        int heightMode =  MeasureSpec.getMode(heightMeasureSpec);
        int heightSize =  MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY){
            h = heightSize ;
        }else {
            if (heightMode==MeasureSpec.AT_MOST){
                h = Math.min(0,heightSize);
            }
        }
        return h ;
    }



    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount>0){
            for (int i = 0;i<childCount;i++){
                View childView = getChildAt(i);
                childView.measure(getWidth(),getHeight());
                /*刷新头*/
                if (childView.getTag()!=null && childView.getTag().equals("head")){
                    headView.layout(0,0,getWidth(),headView.getMeasuredHeight());
                }
                /*列表*/
                if (childView instanceof RecyclerView){
                    recyclerView = (RecyclerView) childView;
                    recyclerView.addOnScrollListener(listener);
                    childView.layout(0,+headView.getMeasuredHeight(),getWidth(),recyclerView.getMeasuredHeight()+headView.getMeasuredHeight());
                }
            }
        }
    }


    int moveDy;
    RecyclerView.OnScrollListener listener=new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            moveDy+=dy;
        }
    };

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        float downY = 0;
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                downY = ev.getRawY();
//
//            case MotionEvent.ACTION_MOVE:
//                if ((ev.getRawY()-downY)>0&&moveDy==0){
//                    return true ;
//                }
//                break;
//        }
        return true;
    }

    private float lastMoveY ;
    private float currentMoveY;

    private Scroller scroller;


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastMoveY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                currentMoveY = event.getRawY() - lastMoveY ;
                Log.v("====move=====","currentMoveY="+currentMoveY);
                layout(0, (int) (-headView.getMeasuredHeight()+ currentMoveY),getWidth(), (int) (getHeight()+currentMoveY));
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                anim();
                break;
        }
        return true;
    }


    private void anim() {
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(1,0);
        valueAnimator.setDuration(500);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                layout(0, (int) (-headView.getMeasuredHeight()+(scale*currentMoveY)),getWidth(), getHeight());
            }
        });
    }
}
