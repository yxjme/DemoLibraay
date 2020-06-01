package com.jewelermobile.gangfu.zdydemo1.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jewelermobile.gangfu.zdydemo1.CheckPermissionUtil;
import com.jewelermobile.gangfu.zdydemo1.Queue.Basket;
import com.jewelermobile.gangfu.zdydemo1.Queue.Consumeer;
import com.jewelermobile.gangfu.zdydemo1.Queue.Producer;
import com.jewelermobile.gangfu.zdydemo1.R;
import com.jewelermobile.gangfu.zdydemo1.api.Api;
import com.jewelermobile.gangfu.zdydemo1.api.ApiSever;
import com.jewelermobile.gangfu.zdydemo1.api.JBHBaseRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.Observer;
import rx.schedulers.Schedulers;

public class LinkedBlockingQueueActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_blocking_queue);

        Basket basket=new Basket();
        Producer producer=new Producer(basket);
        Consumeer consumeer=new Consumeer(basket);


        /*线程池  执行生产*/
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(producer);
        service.execute(consumeer);



        CheckPermissionUtil checkPermissionUtil=new CheckPermissionUtil(this);
        checkPermissionUtil.addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        checkPermissionUtil.checkPermission(new CheckPermissionUtil.Callback() {
            @Override
            public void startRequestPermission() {

            }

            @Override
            public void noRequestPermission() {
                initFile();
            }
        });
        initServerTime();
    }

    private void initServerTime() {
        JBHBaseRequest jbhBaseRequest=new JBHBaseRequest(0) {
            @Override
            protected Map postMap(Map<String, Object> map) {
                return map;
            }
        };
    }



    private void initFile() {
        String path  = Environment.getExternalStorageDirectory().getPath();
        File f=new File(path,"测试.txt");
        try {
            if (!f.exists()){
                f.createNewFile();
            }
            String s= "你好吗，hello word ？";
            FileOutputStream fileOutputStream=new FileOutputStream(f);
            fileOutputStream.write(s.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            RandomAccessFile randomAccessFile=new RandomAccessFile(f.getAbsolutePath(),"rw");

            String s1= "你好吗，hello word ？";
            randomAccessFile.seek(s1.getBytes().length);
            byte[] b = "单按时DNF按时DNF阿斯蒂芬".getBytes();
            randomAccessFile.write(b,0,b.length);
            RandomAccessFile randomAccessFile1=new RandomAccessFile(f.getAbsolutePath(),"rw");
            byte[] bytes=new byte[1024];
//            randomAccessFile.seek(6);
            randomAccessFile1.read(bytes);
            Log.v("===result=====","result="+new String(bytes,"utf-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public class BaseData<T> {
        T d;
        public T getD() {
            return d;
        }
    }




    public class ServerTimeResponse extends BaseResponse {
        long Data ;
        public long getData() {
            return Data;
        }
        public class DataResult{
            long Time;//时间戳
            public long getTime() {
                return Time;
            }
        }
    }


    public class BaseResponse {
        int Code;//  错误代码 详细查看出错文档
        String Msg ; //
        public int getCode() {
            return Code;
        }

        public String getMsg() {
            return Msg;
        }
    }
}


