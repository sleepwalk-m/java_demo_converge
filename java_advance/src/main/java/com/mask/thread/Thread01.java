package com.mask.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: Mask.m
 * @create: 2022/01/12 23:02
 * @description: 演示通过继承thread类创建线程
 */
public class Thread01 {
    public static void main(String[] args) throws InterruptedException {

        Cat cat = new Cat();
        //cat.start();// 另开一个线|程跑其他的


        int count = 0;
        while (true){
            Thread.sleep(2000);
            System.out.println( Thread.currentThread().getName() + "主线程还在跑其他的" + ++count);
        }
    }
}

// 1.当类实现thread 该类就可以当成线程使用
// 2. 重写run方法 写自己的逻辑 run方法是实现runnable接口的方法
class Cat extends Thread {
    @Override
    public void run() {// 重写run方法
        try {
            while (true){
                System.out.println("喵喵叫了" + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
