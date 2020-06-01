package com.jewelermobile.gangfu.zdydemo1.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jewelermobile.gangfu.zdydemo1.R;
import com.jewelermobile.gangfu.zdydemo1.view.FloatImageView;
import com.jewelermobile.gangfu.zdydemo1.view.GuaGuaLeView;

import java.util.Calendar;

public class RoundImageViewActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener{

    private GuaGuaLeView mGuaGuaLeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_image_view);
        mGuaGuaLeView = findViewById(R.id.mGuaGuaLeView);
        mGuaGuaLeView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        int h = mGuaGuaLeView.getHeight();
        Log.v("=====h=======","onCreate="+h);


        FloatImageView mFloatImageView =   findViewById(R.id.mFloatImageView);
        mFloatImageView .setMyOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RoundImageViewActivity.this,"dfasdfasdsaf",Toast.LENGTH_LONG).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onGlobalLayout() {
        int h = mGuaGuaLeView.getHeight();
        Log.v("=====h=======","onGlobalLayout="+h);
        mGuaGuaLeView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}
