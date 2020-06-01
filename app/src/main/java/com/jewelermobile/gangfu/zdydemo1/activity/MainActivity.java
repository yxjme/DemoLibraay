package com.jewelermobile.gangfu.zdydemo1.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.jewelermobile.gangfu.zdydemo1.R;
import com.jewelermobile.gangfu.zdydemo1.adapter.BaseAdapter;
import com.jewelermobile.gangfu.zdydemo1.adapter.CommonViewHolder;
import com.jewelermobile.gangfu.zdydemo1.recyclerview.MyItem2;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseAdapter.OnItemClickListener<MainActivity.Bean>{

    private RecyclerView mRecyclerView;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        iniData();
    }


    public void initView(){
        mRecyclerView =  findViewById(R.id.mRecyclerView);
        LinearLayoutManager l = new LinearLayoutManager(this);
        l.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(l);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addItemDecoration(new MyItem2());
        mRecyclerView.setAdapter(adapter);
        adapter.addHeadView(LayoutInflater.from(this).inflate(R.layout.head_layout,mRecyclerView,false));
        adapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.footer_layout,mRecyclerView,false));
        adapter.setOnItemClickListener(this);


    }



    public void list1(View view){
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }


    public void list2(View view){
        final GridLayoutManager gridLayoutManager =new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                return (adapter.isHeadView(i)||adapter.isFooterView(i)) ? gridLayoutManager.getSpanCount() :  1;
            }
        });
    }



    public void list3(View view){
        final StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
    }




    private List<Bean> list = new ArrayList<>();
    public void iniData(){
        list.add(new Bean("队列",0x1));
        list.add(new Bean("事件传递逻辑",0x2));
        list.add(new Bean("反射机制",0x3));
        list.add(new Bean("贝塞尔曲线",0x4));
        list.add(new Bean("文字字体",0x5));
        list.add(new Bean("自定义圆角图片",0x6));
        list.add(new Bean("获取日历数据",0x7));
        /*着色器*/
        list.add(new Bean("Shader",0x8));
        /*正则表达式学习*/
        list.add(new Bean("正则表达式",0x9));
        adapter.notifyDataSetChanged();



        Intent intent0 = new Intent(Intent.ACTION_MAIN);
//        ComponentName cmp = new ComponentName(getPackageName(),"com.jewelermobile.gangfu.zdydemo1.activity.FanSheActivity");
//        ComponentName cmp = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setComponent(cmp);
        intent0.setClassName(this,"com.jewelermobile.gangfu.zdydemo1.activity.ClipDrawableActivity");
        startActivity(intent0);



//        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]{
//                    Manifest.permission.CALL_PHONE
//            },100);
//        }else {
//            Intent intent=new Intent();
//            intent.setAction(Intent.ACTION_VIEW);
////        intent.setData(Uri.parse("http://www.baidu.com"));
////            intent.setData(Uri.parse("tel:15766009164"));
//            intent.setData(Uri.parse("content://contacts/people"));
//            startActivity(intent);
//        }
    }


    BaseAdapter adapter=new BaseAdapter<Bean>(this,list) {
        @Override
        public int getLayoutId() {
            return R.layout.item_layout;
        }
        @Override
        public void itemView(CommonViewHolder viewHolder, Bean bean, int position) {
            viewHolder.title_textview.setText(bean.name);
        }
    } ;



    @Override
    public void onItemClick(View v, Bean bean) {
        switch (bean.Type){
            case 0x1:
                startActivity(new Intent(this, LinkedBlockingQueueActivity.class));
                break;
            case 0x2:
                startActivity(new Intent(this, MyViewGroupActivity.class));
                break;
            case 0x3:
                startActivity(new Intent(this, FanSheActivity.class));
                break;
            case 0x4:
                startActivity(new Intent(this, BSE3ViewActivity.class));
                break;
            case 0x5:
                startActivity(new Intent(this, TextFontActivity.class));
                break;
            case 0x6:
                startActivity(new Intent(this, RoundImageViewActivity.class));
                break;
            case 0x7:
                startActivity(new Intent(this, CalendarActivity.class));
                break;
            case 0x8:
                startActivity(new Intent(this, ShaderActivity.class));
                break;
            case 0x9:
                startActivity(new Intent(this, ZhengZeActivity.class));
                break;
        }
    }


    class Bean{
        String name;
        int Type ;
        public Bean(String name, int type) {
            this.name = name;
            Type = type;
        }
    }
}
