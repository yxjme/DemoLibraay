package com.jewelermobile.gangfu.zdydemo1.bean;


import java.util.ArrayList;
import java.util.List;

public class ObserverManager implements Observerable{

    private Object obj;
    private List<Observer> list=new ArrayList<>();
    private static ObserverManager manager;


    public static ObserverManager newInstance() {
        if (null==manager){
            synchronized (ObserverManager.class){
                if (null==manager){
                    manager = new ObserverManager();
                }
            }
        }
        return manager;
    }


    /**
     * 注册
     * @param observer
     */
    public void addObserver(Observer observer){
        list.add(observer);
    }


    /**
     *
     * @param observer
     */
    public void removeObserver(Observer observer){
        list.remove(observer);
    }



    @Override
    public void notifyObserver() {
        for (Observer observer:list){
            observer.upData(obj);
        }
    }


    @Override
    public void setInfo(Object obj) {
        this.obj=obj;
        notifyObserver();
    }
}
