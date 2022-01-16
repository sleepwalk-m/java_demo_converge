package com.mask.thread.method;

/**
 * @author: Mask.m
 * @date: 2022/1/15 23:23
 * @version: v1.0
 * @Description: 线程的方法演示2 yield join
 */
public class ThreadMethod02 {
    public static void main(String[] args) throws Exception{

        T2 t = new T2();
        t.start();


        for (int i = 1; i <= 20; i++) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "吃了" +i + "个包子~" );

            if (i == 5){
                System.out.println("main 线程吃到了第5个包子，开始礼让子线程先吃完~~~");
                // 线程礼让 不一定会成功
                //Thread.yield();

                // 线程插队 一定会成功 这里就是到第5个，子线程插队先执行
                t.join();

                System.out.println("子线程 吃完了  main线程继续吃~~~");
            }
        }
    }
}


class T2 extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {

            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "吃了" +i + "个包子~" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
