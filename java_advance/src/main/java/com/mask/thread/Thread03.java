package com.mask.thread;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/14 18:13
 * @Description: 开启多个子线程
 */
public class Thread03 {
    public static void main(String[] args) {

        Monkey monkey = new Monkey();
        Baboon baboon = new Baboon();

        Thread t1 = new Thread(monkey);
        Thread t2 = new Thread(baboon);

        t1.start();
        t2.start();


        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("thread name:" + Thread.currentThread().getName() + " ，还在跑第"+i+"次");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Monkey implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("thread name:" + Thread.currentThread().getName() + " ，Mokney锤了"+i+"次胸口");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


class Baboon implements Runnable{

    @Override
    public void run() {
        for (int i = 1; i <= 15; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("thread name:" + Thread.currentThread().getName() + " ，Baboon锤了"+i+"次胸口");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
