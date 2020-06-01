package com.jewelermobile.gangfu.zdydemo1.Queue;

import android.util.Log;

public class Consumeer implements Runnable {


    Basket basket;
    public Consumeer (Basket basket){
        this.basket=basket;
    }


    int i;
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(20*1000);
                basket.consume();
                i++;
                Log.d("=========take======","i="+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
