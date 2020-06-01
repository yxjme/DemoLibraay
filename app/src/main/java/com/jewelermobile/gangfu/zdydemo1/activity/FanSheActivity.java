package com.jewelermobile.gangfu.zdydemo1.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jewelermobile.gangfu.zdydemo1.R;
import com.jewelermobile.gangfu.zdydemo1.adapter.BaseAdapter;
import com.jewelermobile.gangfu.zdydemo1.adapter.CommonViewHolder;
import com.jewelermobile.gangfu.zdydemo1.view.DefaultTextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class FanSheActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_she);
        init();
        initList();
    }

    private void init() {

        final DefaultTextView mDefaultTextView = findViewById(R.id.mDefaultTextView);
        mDefaultTextView.setDefaultBackgroundResource(R.drawable.gray);
        EditText mEditText=findViewById(R.id.mEditText);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDefaultTextView.setText(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void initList(){
        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> list=new ArrayList<>();
        for (int i= 0 ;i<10;i++){
            list.add("bbbb="+i);
        }
        mRecyclerView.setAdapter(new BaseAdapter<String>(this,list) {
            @Override
            public int getLayoutId() {
                return android.R.layout.simple_list_item_1;
            }
            @Override
            public void itemView(CommonViewHolder viewHolder, String o, int position) {
                viewHolder.text1.setText(o);
            }
        });
    }



    private void demo0(Object o) {
        StringBuffer stringBuffer=new StringBuffer();
        Class c=o.getClass();
        String className = c.getName();

        Constructor[] cons = c.getDeclaredConstructors();
        for (Constructor constructor:cons){
            int modifiers = constructor.getModifiers();
            stringBuffer.append("modifiers="+  Modifier.toString(modifiers)+"\n");
            Class[] parameterTypes = constructor.getParameterTypes();
        }

        Method[] methods = c.getMethods();
        for (Method method:methods){
            String methodName = method.getName();
            stringBuffer.append("methodName="+methodName+"\n");
        }

        Field[] f = c.getFields();
        f[0].getType();
        f[0].getName();

        try {
            Field fid = c.getField(f[0].getName());
            fid.getName();
            fid.getType();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        int m = f[0].getModifiers();
        String s =  Modifier.toString(m);

        stringBuffer.append("className="+className+"\n");
        Log.v("====c====","stringBuffer = "+stringBuffer.toString());
    }
}
