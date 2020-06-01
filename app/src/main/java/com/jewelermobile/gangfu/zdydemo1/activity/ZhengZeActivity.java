package com.jewelermobile.gangfu.zdydemo1.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jewelermobile.gangfu.zdydemo1.R;
import com.jewelermobile.gangfu.zdydemo1.bean.Example0;
import com.jewelermobile.gangfu.zdydemo1.bean.ObserverManager;
import com.jewelermobile.gangfu.zdydemo1.view.MyWaveView;

import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhengZeActivity extends AppCompatActivity {

    private TextView tv_content;

    MyWaveView mMyWaveView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zheng_ze);
        initView();


        mMyWaveView  = findViewById(R.id.mMyWaveView);
        mMyWaveView.start();



        final Example0 p = new Example0();
        ObserverManager.newInstance().addObserver(p);
        ObserverManager.newInstance().setInfo("你好吗");



        tv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObserverManager.newInstance().setInfo("你好吗"+(a++));
                Log.v("===========","dfasdfasd="+new Gson().toJson(p));
            }
        });
    }


    int a;

    /**
     * \\s+ 表示一个多个空格
     */
    String input="this is     text dkf kkkasdf77777naskdfns54545dafns55.554BdA";
    String regex = "77777(?=text|55|na)";
    private void initView() {
        tv_content = findViewById(R.id.tv_content);
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(input);
        StringBuffer stringBuffer=new StringBuffer();
        while (matcher.find()){
            String s=matcher.group();
            Log.v("======tag=====","s="+s);
            stringBuffer.append("result_ok="+s+"\n");
        }
        tv_content.setText(stringBuffer.toString());
    }

}
