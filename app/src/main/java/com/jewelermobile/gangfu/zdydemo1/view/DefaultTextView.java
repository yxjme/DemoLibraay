package com.jewelermobile.gangfu.zdydemo1.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import com.jewelermobile.gangfu.zdydemo1.R;

@SuppressLint("AppCompatCustomView")
public class DefaultTextView extends TextView {

    /*默认的背景*/
    private int defaultBackgroundResource = R.drawable.bg;

    public DefaultTextView(Context context) {
        this(context,null);
    }

    public DefaultTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public DefaultTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundResource(defaultBackgroundResource);
    }

    public void setDefaultBackgroundResource(int defaultBackgroundResource) {
        this.defaultBackgroundResource = defaultBackgroundResource;
        setBackgroundResource(defaultBackgroundResource);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (TextUtils.isEmpty(text)){
            setBackgroundResource(defaultBackgroundResource);
        }else {
            setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
