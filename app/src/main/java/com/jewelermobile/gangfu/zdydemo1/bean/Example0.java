package com.jewelermobile.gangfu.zdydemo1.bean;


public class Example0 implements Observer<String> {

    String message;

    public Example0(){}


    public static int fun(int a ,int b){
        return a + b ;
    }


    @Override
    public void upData(String s) {
        this.message=s;
    }

    public String getMessage() {
        return message;
    }
}
