package com.jewelermobile.gangfu.zdydemo1.activity;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.jewelermobile.gangfu.zdydemo1.R;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class ClipDrawableActivity extends AppCompatActivity implements SensorEventListener {
    ImageView imageView, mImg;
    /*实现缓缓打开效果*/
    ClipDrawable clipDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_drawable);
        init();


        initContentProvider();
        initService();
        initURLLoadImage();
        initSensor();

        initLocation();
    }

    private void initLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        LocationProvider provider = locationManager.getProvider("");
    }



    SensorManager sensorManager ;
    private void initSensor() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        /*方向传感*/
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME) ;
        /*陀螺仪*/
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),SensorManager.SENSOR_DELAY_GAME) ;
        /*磁场*/
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_GAME) ;
        /*重力*/
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),SensorManager.SENSOR_DELAY_GAME) ;
        /*线性加速度*/
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),SensorManager.SENSOR_DELAY_GAME) ;
        /*温度*/
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE),SensorManager.SENSOR_DELAY_GAME) ;
        /*光感*/
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_GAME) ;
        /*压力*/
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE),SensorManager.SENSOR_DELAY_GAME) ;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }



    float currentDegree;
    /**
     * 实时输出传感器数据
     *
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        /*传感器的值*/
        float[] val = event.values;
        StringBuffer stringBuffer ;
        switch (event.sensor.getType()){
            /*方向*/
            case Sensor.TYPE_ORIENTATION :
                stringBuffer = new StringBuffer();
                stringBuffer.append("\n");
                stringBuffer.append("val[0] z轴绕过的角度:"+val[0]+"\n");
                stringBuffer.append("val[1] x轴绕过的角度:"+val[1]+"\n");
                stringBuffer.append("val[2] y轴绕过的角度:"+val[2]+"\n");
                Log.v("=======方向========","方向传感="+stringBuffer.toString());



                float degree = val[0];
                RotateAnimation animation = new RotateAnimation(currentDegree,-degree,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                animation.setDuration(200);
                    showImage.startAnimation(animation);
                currentDegree = -degree ;

                break;
                /*陀螺仪*/
            case Sensor.TYPE_GYROSCOPE :
                stringBuffer = new StringBuffer();

                stringBuffer.append("\n");
                stringBuffer.append("val[0] x轴绕过的角速度:"+val[0]+"\n");
                stringBuffer.append("val[1] y轴绕过的角速度:"+val[1]+"\n");
                stringBuffer.append("val[2] z轴绕过的角速度:"+val[2]+"\n");
                Log.v("===============","陀螺仪="+stringBuffer.toString());
                break;

            /*磁场*/
            case Sensor.TYPE_MAGNETIC_FIELD :
                stringBuffer = new StringBuffer();

                stringBuffer.append("\n");
                stringBuffer.append("val[0] x磁场度:"+val[0]+"\n");
                stringBuffer.append("val[1] y磁场度:"+val[1]+"\n");
                stringBuffer.append("val[2] z磁场度:"+val[2]+"\n");
                Log.v("===============","磁场="+stringBuffer.toString());
                break;

            /*重力*/
            case Sensor.TYPE_GRAVITY :
                stringBuffer = new StringBuffer();

                stringBuffer.append("\n");
                stringBuffer.append("val[0] x 方向的重力:"+val[0]+"\n");
                stringBuffer.append("val[1] y 方向的重力:"+val[1]+"\n");
                stringBuffer.append("val[2] z 方向的重力:"+val[2]+"\n");
                Log.v("===============","重力="+stringBuffer.toString());
                break;

            /*线性加速度*/
            case Sensor.TYPE_LINEAR_ACCELERATION :
                stringBuffer = new StringBuffer();
                stringBuffer.append("\n");
                stringBuffer.append("val[0] x 线性加速度:"+val[0]+"\n");
                stringBuffer.append("val[1] y 线性加速度:"+val[1]+"\n");
                stringBuffer.append("val[2] z 线性加速度:"+val[2]+"\n");
                Log.v("===============","线性加速度="+stringBuffer.toString());
                break;
            /*温度*/
            case Sensor.TYPE_AMBIENT_TEMPERATURE :
                stringBuffer = new StringBuffer();
                stringBuffer.append("\n");
                stringBuffer.append("val[0] 当前温度:"+val[0]+"\n");
                Log.v("===============","当前温度="+stringBuffer.toString());
                break;

            /*光感*/
            case Sensor.TYPE_LIGHT :
                stringBuffer = new StringBuffer();
                stringBuffer.append("\n");
                stringBuffer.append("val[0] 当前光感:"+val[0]+"\n");
                Log.v("========当前光感=======","当前光感="+stringBuffer.toString());
                break;
            /*压力*/
            case Sensor.TYPE_PRESSURE :
                stringBuffer = new StringBuffer();
                stringBuffer.append("\n");
                stringBuffer.append("val[0] 当前压力:"+val[0]+"\n");
                Log.v("===============","当前压力="+stringBuffer.toString());
                break;
        }
    }



    /**
     * 传感器精度发生改变时调用
     *
     * @param sensor
     * @param accuracy
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {



    }





    ImageView showImage;
    private void initURLLoadImage() {
        showImage = findViewById(R.id.showImage);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL("https://img12.360buyimg.com/babel/s590x470_jfs/t1/95464/30/9251/74407/5e0d6139E878bf1f3/09c7a2b5d96aec27.jpg.webp");
                    InputStream in = url.openStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(in);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showImage.setImageBitmap(bitmap);
                        }
                    });
                    in.close();

                    /*写入文件中*/
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + System.currentTimeMillis()+".png";
                        File file=new File(path);
                        if (!file.exists()){
                            file.createNewFile();
                        }
                        InputStream i = url.openStream();
                        FileOutputStream fileOutputStream=new FileOutputStream(file);
                        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                        byte[] buff = new byte[1024];
                        int len ;
                        while ((len = i.read(buff))!= -1){
                            bufferedOutputStream.write(buff,0,len);
                        }
                        bufferedOutputStream.close();
                        fileOutputStream.close();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * @param url
     * @param params
     */
    public void GET_Request(String url,String params){
        String getUrl = url+"?"+params;//params的格式（val=key&val=key）
        try {
            URL url1 = new URL(getUrl);
            URLConnection conn = url1.openConnection();

            /*配置通用属性  */
            conn.addRequestProperty("","");
            conn.addRequestProperty("","");
            conn.addRequestProperty("","");

            /*建立实际连接请求*/
            conn.connect();

            /*获取所有请求响应头*/
            Map<String, List<String>> map = conn.getHeaderFields();

            /*响应的数据*/
            InputStream in = conn.getInputStream();

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
            String result = null;
            String line ;
            while ((line  = bufferedReader.readLine())!=null){
                result += line ;
            }

            Log.v("========","result="+result);

            bufferedReader.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * @param url
     * @param params
     */
    public void POST_Request(String url,String params){
        String getUrl = url+"?"+params;//params的格式（val=key&val=key）
        try {
            URL url1 = new URL(getUrl);
            URLConnection conn = url1.openConnection();

            /*配置通用属性  */
            conn.addRequestProperty("","");
            conn.addRequestProperty("","");
            conn.addRequestProperty("","");

            /*post必须添加这两行  而且顺序不可变*/
            conn.setDoOutput(true);
            conn.setDoInput(true);

            PrintWriter bufferedWriter=new PrintWriter (conn.getOutputStream());

            /*发送请求*/
            bufferedWriter.write(params);
            bufferedWriter.print(params);

            /*缓冲*/
            bufferedWriter.flush();

            /*获取所有请求响应头*/
            Map<String, List<String>> map = conn.getHeaderFields();


            /*响应的数据*/
            InputStream in = conn.getInputStream();

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
            String result = null;
            String line ;
            while ((line  = bufferedReader.readLine())!=null){
                result += line ;
            }

            Log.v("========","result="+result);

            bufferedReader.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    MyService.MyBinder binder  ;

    private void initService() {
        bindService(new Intent(this,MyService.class),connection,Service.BIND_AUTO_CREATE);

        unbindService(connection);
    }


    /**
     * 服务连接监听
     */
    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder  = (MyService.MyBinder)service;
            Toast.makeText(ClipDrawableActivity.this,"联系了",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v("======onServed========","断开联系了");
            binder = null ;
        }
    };



    Uri uri = Uri.parse("content://com.jewelermobile.gangfu.zdydemo1.DictProvider/");
    private void initContentProvider() {
        final ContentResolver p = this.getContentResolver();
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.insert(uri,new ContentValues());

            }
        });

        findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.delete(uri,"",new String[]{});

            }
        });

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.update(uri,null,null,new String[]{});
            }
        });

        findViewById(R.id.qur).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.query(uri,null,null,null,null);
            }
        });
    }


    private void init() {
        final ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(this,R.animator.animator1);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim1);
        anim.start();
        final Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                btn.startAnimation(anim);
                animator.setTarget(v);
                animator.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }
}
