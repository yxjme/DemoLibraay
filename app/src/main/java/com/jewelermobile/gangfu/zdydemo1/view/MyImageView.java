package com.jewelermobile.gangfu.zdydemo1.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView implements View.OnTouchListener {
    public MyImageView(Context context) {
        this(context,null);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float y = event.getRawY();
        float x = event.getRawX();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                layout((int)x,(int)y,(int)(getWidth()+x),(int)(getHeight()+y));
                break;
        }
        return true;
    }
}
