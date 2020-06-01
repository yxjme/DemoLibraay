package com.jewelermobile.gangfu.zdydemo1.activity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyService extends Service {


    public MyBinder binder=new MyBinder();
    public class MyBinder extends Binder {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v("======onServed========","断开联系了");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
