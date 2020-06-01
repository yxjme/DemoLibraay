package com.jewelermobile.gangfu.zdydemo1.Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Basket {


    /**
     * 篮子的容量为10个
     */
    private BlockingQueue<Apple> basket= new LinkedBlockingQueue<Apple>(10);


    /**
     * 装进篮子
     *
     * @param apple
     */
    public void produce(Apple apple){
        try {
            //添加元素到队列，如果队列已满,线程进入等待，直到有空间继续生产
            basket.put(apple);
            //添加元素到队列，如果队列已满，抛出IllegalStateException异常，退出生产模式
//        queue.add(bean);
            //添加元素到队列，如果队列已满或者说添加失败，返回false，否则返回true，继续生产
//        queue.offer(bean);
            //添加元素到队列，如果队列已满，就等待指定时间，如果添加成功就返回true，否则false，继续生产
//        queue.offer(bean,5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    /**
     * 消费
     *
     * @return
     */
    public  Apple consume(){
        try {
            //检索并移除队列头部元素，如果队列为空,线程进入等待，直到有新的数据加入继续消费
            Apple apple = basket.take();
            //检索并删除队列头部元素，如果队列为空，抛出异常，退出消费模式
//        Apple bean = queue.remove();
            //检索并删除队列头部元素，如果队列为空，返回false，否则返回true，继续消费
//        Apple bean = queue.poll();
            //检索并删除队列头部元素，如果队列为空，则等待指定时间，成功返回true，否则返回false，继续消费
//        Apple bean = queue.poll(3, TimeUnit.SECONDS);
            return apple ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
