package com.jewelermobile.gangfu.zdydemo1.activity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MediaRecorderManager {

    private MediaRecorder mediaRecorder ;
    private static MediaRecorderManager mediaRecorderManager ;
    /*录音状态*/
    private static int State = 0 ;


    public static MediaRecorderManager newInstance(){
        if (null == mediaRecorderManager){
            synchronized (MediaRecorderManager.class){
                if (null == mediaRecorderManager){
                    mediaRecorderManager = new MediaRecorderManager() ;
                }
            }
        }
        return  mediaRecorderManager ;
    }


    private MediaRecorderManager(){
        mediaRecorder = new MediaRecorder();
    }


    /**
     * 开始录制
     * @throws IOException
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void  startRecorder() throws IOException {
        /*判断是否有SdCard*/
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file=new File(rootPath,System.currentTimeMillis()+".amr");
            if (!file.exists()){
                file.createNewFile();
            }
            mediaRecorder.setOutputFile(file);
            mediaRecorder.prepare();
            mediaRecorder.start();
            State = 1 ;
        }
    }


    /**
     * 恢复录制
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void resumeRecorder(){
        if (mediaRecorder!=null&&State==2){
            mediaRecorder.resume();
            State = 1 ;
        }
    }


    /**
     * 暂停录制
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void pauseRecorder(){
        if (mediaRecorder!=null&&State==1){
            mediaRecorder.pause();
            State = 2 ;
        }
    }


    /**
     *
     * 停止录制
     *
     */
    public void stopRecorder(){
        if (mediaRecorder!=null){
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null ;
            State = 0 ;
        }
    }

    /**
     * 释放资源
     */
    public void release(){
        if (mediaRecorder!=null){
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null ;
            State = 0 ;
        }
    }


    /**
     * 通过文件绝对路径播放
     *
     * @param path
     */
    public void  playRecorder(String path){
        try {
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(path);
            player.prepare();
            player.start();

            player.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                    return false;
                }
            });
            /*播放完成回调*/
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                }
            });

            player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
