package com.jewelermobile.gangfu.zdydemo1;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import java.util.LinkedList;


public class CheckPermissionUtil {


    private LinkedList<String> list;
    private Activity activity;

    public CheckPermissionUtil(Activity activity){
        this.activity=activity;
        list=new LinkedList<>();
    }


    /**
     * 添加
     *
     * @param permission
     */
    public void addPermission(String permission){
        list.add(permission);
    }



    /**
     * 删除
     *
     * @param permission
     */
    public void removePermission(String permission){
        if (list.contains(permission)){
            list.remove(permission);
        }
    }



    /**
     * @param callback
     */
    public void checkPermission(Callback callback){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            for (String p:list){
                if (ActivityCompat.checkSelfPermission(activity, p) != PackageManager.PERMISSION_GRANTED){
                    list.add(p);
                }
            }

            if (list.size()>0){
                ActivityCompat.requestPermissions( activity,list.toArray(new String[list.size()]),100);
                callback.startRequestPermission();
            }else {
                callback.noRequestPermission();
            }

        }else {
            callback.noRequestPermission();
        }
    }




    public interface Callback{
        void startRequestPermission();
        void noRequestPermission();
    }
}
