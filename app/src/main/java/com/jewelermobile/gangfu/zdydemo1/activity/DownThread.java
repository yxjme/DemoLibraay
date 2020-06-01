package com.jewelermobile.gangfu.zdydemo1.activity;

import org.apache.http.client.HttpClient;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownThread extends Thread {

    private String url;
    private File file;

    public DownThread(String url,File file){
        this.url = url ;
        this.file = file;
    }


    @Override
    public void run() {
        super.run();
        try {
            URL urlReq = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlReq.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10*1000);
            /**/
            connection.addRequestProperty("Accept","");
            connection.addRequestProperty("charset","utf-8");
            /*获取响应io流*/
            InputStream in = connection.getInputStream();
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            BufferedOutputStream outputStream=new BufferedOutputStream(fileOutputStream);

            int len = 0;
            int line ;

            byte[] buff = new byte[1024];
            while ((line = in.read(buff))!=-1){
                /*下载文件的大小*/
                len += line ;
                /*写入文件*/
                outputStream.write(buff,0,len);
            }

            in.close();
            fileOutputStream.close();
            outputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
