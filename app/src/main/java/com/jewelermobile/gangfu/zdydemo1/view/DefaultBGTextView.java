package com.jewelermobile.gangfu.zdydemo1.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.jewelermobile.gangfu.zdydemo1.R;

@SuppressLint("AppCompatCustomView")
public class DefaultBGTextView extends TextView {

    /**默认背景*/
    private int defaultBackground = 0;
    private int backgroundResource = 0 ;
    /**默认最小宽度*/
    private int defaultMinWidth = dpToPx(50);

    public DefaultBGTextView(Context context) {
        this(context,null);
    }

    public DefaultBGTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DefaultBGTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DefaultBGTextView, 0, defStyleAttr);
        int count  = array.getIndexCount();
        for (int i= 0 ;i<count ; i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.DefaultBGTextView_defaultBackground:
                    defaultBackground = array.getResourceId(attr,0);
                    setBackgroundResource(defaultBackground);
                    break;
                case R.styleable.DefaultBGTextView_defaultMinWidth:
                    defaultMinWidth =dpToPx( array.getIndex(attr));
                    setMinWidth(defaultMinWidth);
                    break;
                case R.styleable.DefaultBGTextView_backgroundResource:
                    backgroundResource = array.getResourceId(attr,0);
                    setBackgroundResource(backgroundResource);
                    break;
            }
        }
        array.recycle();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (handler!=null){
            handler.sendEmptyMessage(0);
        }
    }


    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           String text =  getText().toString();
            if (TextUtils.isEmpty(text)){
                setBackgroundResource(defaultBackground);
                setMinWidth(defaultMinWidth);
            }else {
                setBackgroundResource(backgroundResource);
                setMinWidth(0);
            }
        }
    };


    /**
     * @param val
     * @return
     */
    public int dpToPx(int val){
        float density = getResources().getDisplayMetrics().density;
        return (int) (density * val + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {



        super.onDraw(canvas);
    }
}
