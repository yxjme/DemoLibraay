package com.jewelermobile.gangfu.zdydemo1.bean;

public interface Observerable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
    void setInfo(Object obj);
}
