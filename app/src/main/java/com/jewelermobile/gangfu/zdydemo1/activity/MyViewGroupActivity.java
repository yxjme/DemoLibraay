package com.jewelermobile.gangfu.zdydemo1.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.jewelermobile.gangfu.zdydemo1.R;
import com.jewelermobile.gangfu.zdydemo1.envtouch.MyViewGroupA;

public class MyViewGroupActivity extends AppCompatActivity {
    private MyViewGroupA mMyViewGroupA;
    private LinearLayout lay_bar_1,lay_bar_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_group);
    }


    NestedScrollView mScrollView;
    /**
     * mMyViewGroupA
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initMyView() {
        mScrollView=findViewById(R.id.mScrollView);
        lay_bar_1 = findViewById(R.id.lay_bar_1);
        lay_bar_2 = findViewById(R.id.lay_bar_2);
        mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int lay_bar_2_top=lay_bar_2.getTop();
                if (scrollY>=lay_bar_2_top){
                    if (lay_bar_1.getVisibility()!=View.VISIBLE){
                        lay_bar_1.setVisibility(View.VISIBLE);
                    }
                }else {
                    if (lay_bar_1.getVisibility()!=View.GONE){
                        lay_bar_1.setVisibility(View.GONE);
                    }
                }
            }
        });
    }


}
