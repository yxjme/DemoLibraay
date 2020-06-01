package com.jewelermobile.gangfu.zdydemo1.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jewelermobile.gangfu.zdydemo1.R;

@SuppressLint("AppCompatCustomView")
public class ContentText extends TextView {

    String  text_empty_color =  "#F1F5F6";
    int text_no_empty_color = Color.TRANSPARENT ;

    public ContentText(Context context) {
        super(context);
    }

    public ContentText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs,defStyleAttr);
    }


    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ContentText,0,defStyleAttr);
        int count = array.getIndexCount();
//        if (count>0){
//            for (int i=0;i<count;i++){
//                int attrId = array.getIndex(i);
//                switch (attrId){
//                    case R.styleable.ContentText_text_empty_color:
//                        text_empty_color = array.getColor(attrId,Color.YELLOW);
//                        break;
//                    case R.styleable.ContentText_text_no_empty_color:
//                        text_no_empty_color = array.getColor(attrId,Color.TRANSPARENT);
//                        break;
//                }
//            }
//        }
//        array.recycle();
    }



    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        Log.v("=======dss=======","t="+text);
        if (TextUtils.isEmpty(text)){
            setBackgroundColor(Color.BLUE);
            setMinWidth(200);
        }else {
            setBackgroundColor(Color.RED);
            setMinWidth(0);
        }
    }
}
