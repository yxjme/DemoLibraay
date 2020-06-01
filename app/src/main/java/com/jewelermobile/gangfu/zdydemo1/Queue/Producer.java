package com.jewelermobile.gangfu.zdydemo1.Queue;


import android.util.Log;

/**
 * 生产者
 */
public class Producer implements Runnable {


    private String produce= "4234343 ";
    private Basket basket;


    public Producer(Basket basket){
        this.basket=basket;
    }



    @Override
    public void run() {
        try {
            for (int i = 0; ; i++) {
                Apple bean = new Apple("苹果" + i + "号");
                Log.e("======produce=======", produce + "正在生产苹果 " + bean);
                basket.produce(bean);
                Log.e("======produce=======", produce + "生产苹果 " + bean + " 结束----------------");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
