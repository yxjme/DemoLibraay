package com.jewelermobile.gangfu.zdydemo1.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.in;

public class MySocket {


    public static void main(String[] args) throws IOException {
        //创建监听获取客户端发送信息，定义端口号为3000
        ServerSocket serverSocket = new ServerSocket(3000);
        while (true){
            /*获取接受到信息*/
            Socket socket = serverSocket.accept();
            /*发送来的消息io流*/
            InputStream in = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            /*接受发送来的信息*/
            String result = bufferedReader.readLine();
            /*打印数据*/
            System.out.print(result);
        }
    }


    public  void initSocket(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    /*创建连接  */
                    Socket socket=new Socket("192.168.1.5",3000);
//        socket.getInputStream(); 获取服务端发送的数据
//        socket.getOutputStream() ; 客户端发送数据给服务端

                    InputStream in = socket.getInputStream() ;
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    /*接受发送来的信息*/
                    String result = bufferedReader.readLine();
                    /*打印数据*/
                    System.out.print(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
