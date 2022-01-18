package com.mask.thread.method;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/17 13:44
 * @Description: 守护线程演示
 */
public class DaemonThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        MyDaemonThread t = new MyDaemonThread();
        t.setDaemon(true); // 设置为守护线程 此时main线程为用户线程，那么该线程在main线程结束时会一并结束
        t.start();


        // main线程运行10次 结束
        // 不设置守护线程，那么子线程会继续运行
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(1000);
            System.out.println("小贾上班几天了~~~~" + i);
        }
    }
}


class MyDaemonThread extends Thread{
    @Override
    public void run() {
        while (true) {
            try {
                // 子线程循环运行
                Thread.sleep(1000);
                System.out.println("小皮和小路在愉快的做头发~~~~~");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
