package com.jewelermobile.gangfu.zdydemo1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import com.jewelermobile.gangfu.zdydemo1.R;

public class GuaGuaLeView extends View {

    private Bitmap mBGBitmap;
    private Bitmap mOutBitmap;
    private Paint mPaint ;
    private Path mPath;
    private Canvas mCanvas;
    /**画笔的宽度*/
    int paintWidth = 80;
    /**刮刮背景色*/
    int guaGuaBgColor = Color.GRAY;
    /**默认图*/
    int bgId = R.mipmap.a0;


    public GuaGuaLeView(Context context) {
        this(context,null);
    }

    public GuaGuaLeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    ViewTreeObserver.OnGlobalLayoutListener layoutListener=new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {

        }
    };

    public GuaGuaLeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
        mBGBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a0);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.GuaGuaLeView, 0, defStyleAttr);
        int count = array.getIndexCount();
        for (int i = 0 ; i < count ; i++){
            int attr = array.getIndex(i);
            switch (attr){
                case R.styleable.GuaGuaLeView_guaGuaBgColor:
                    guaGuaBgColor = array.getColor(attr,Color.GRAY);
                    break;
                case R.styleable.GuaGuaLeView_paintWidth:
                    paintWidth =  array.getInt(R.styleable.GuaGuaLeView_paintWidth,paintWidth);
                    break;
                case R.styleable.GuaGuaLeView_bgId:
                    bgId = array.getResourceId(attr,R.mipmap.a0);
                    break;
            }
        }
        array.recycle();
        init();
        initPaint();
    }


    /**
     * @param mBGBitmap
     */
    public void setmBGBitmap(Bitmap mBGBitmap) {
        this.mBGBitmap = mBGBitmap;
    }


    private void init() {
        mBGBitmap = BitmapFactory.decodeResource(getResources(), bgId);
        mOutBitmap = Bitmap.createBitmap(mBGBitmap.getWidth(),mBGBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        mCanvas=new Canvas(mOutBitmap);
        mCanvas.drawColor(guaGuaBgColor);
    }

    private void initPaint() {
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.TRANSPARENT);
        mPaint.setStrokeWidth(paintWidth);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mBGBitmap!=null){
            setMeasuredDimension(mBGBitmap.getWidth(),mBGBitmap.getHeight());
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                mCanvas.drawPath(mPath,mPaint);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(80);
        String s = "恭喜中奖了";
        Rect rect = new Rect();
        paint.getTextBounds(s,0,s.length(),rect);
        canvas.drawBitmap(mBGBitmap,0,0,paint);
        canvas.drawText(s,(mBGBitmap.getWidth()-rect.right)/2,(mBGBitmap.getHeight()-rect.bottom)/2,paint);
        canvas.drawBitmap(mOutBitmap,0,0,paint);
    }


    /**
     * @param var
     * @return
     */
    public int dpToPx(int var){
        float density = getResources().getDisplayMetrics().density;
        return (int) (density*var + 0.5f);
    }
}
