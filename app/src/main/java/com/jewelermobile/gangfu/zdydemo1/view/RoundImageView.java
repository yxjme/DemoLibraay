package com.jewelermobile.gangfu.zdydemo1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.jewelermobile.gangfu.zdydemo1.R;

public class RoundImageView extends View {

    public final static int CIRCLE=1;
    public final static int ROUND=2;
    private Bitmap bitmap ;
    private Bitmap outBitmap ;
    private Paint paint = new Paint();
    private int roundRadius = 80 ;
    private int circleRadius = 80 ;
    private int type;


    public RoundImageView(Context context) {
        this(context,null);
    }

    public RoundImageView(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }


    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView, 0, defStyleAttr);
        int count = array.getIndexCount();
        for (int i=0;i<count;i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.RoundImageView_circleRadius:
                    circleRadius = array.getInt(attr,80);
                    break;

                case R.styleable.RoundImageView_roundRadius:
                    roundRadius = array.getInt(attr,80);
                    break;

                case R.styleable.RoundImageView_type:
                    type = array.getInt(attr,CIRCLE);
                    break;
            }
        }
        array.recycle();
        init();
    }

    private void init() {
        paint.setColor(Color.WHITE);
        paint.setColor(Color.YELLOW);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a0);
        outBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            switch (type){
                case ROUND :
                    canvas.drawRoundRect(0,0,bitmap.getWidth(),bitmap.getHeight(),roundRadius,roundRadius,paint);
                    break;
                case CIRCLE:
                    int w = bitmap.getWidth();
                    int h = bitmap.getHeight() ; 
                    int r = Math.min(w,h)/2;
                    
                    int tX = 0;
                    int tY = 0;
//                    if (w>h){
//                        tX = (w - h)/2;
//                    }
//                    if (w<h){
//                        tY = (h - w)/2;
//                    }

                    canvas.drawCircle(r+tX,r+tY,r,paint);
                    break;
            }
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,0,0,paint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (bitmap!=null){
            int w = bitmap.getWidth();
            int h = bitmap.getHeight();
            switch (type) {
                case CIRCLE:
                    int r = Math.min(w,h);
                    setMeasuredDimension(r,r);
                    break;
                case ROUND:
                    setMeasuredDimension(bitmap != null ? bitmap.getWidth() : 0, bitmap != null ? bitmap.getHeight() : 0 );
                    break;
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (outBitmap!=null){
            Paint paint =new Paint();
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(outBitmap,0,0,paint);
        }
    }
}
