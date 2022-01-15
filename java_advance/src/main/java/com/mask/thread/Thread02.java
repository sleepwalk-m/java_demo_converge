package com.mask.thread;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/14 17:04
 * @Description: runnable方式创建线程
 */
public class Thread02 {
    public static void main(String[] args) throws InterruptedException {

        Dog dog = new Dog();

        Thread thread = new Thread(dog); // 静态代理
        thread.start();

        for (int i = 1; i <= 10; i++) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "线程运行了" + i);
        }

    }
}

class Dog implements Runnable{

    int count = 0;
    @Override
    public void run() {
        for (int i = 1;i <= 8;i++){
            try {
                System.out.println(Thread.currentThread().getName() + "  hi" + count++);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
