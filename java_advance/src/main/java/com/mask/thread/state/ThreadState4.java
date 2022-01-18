package com.mask.thread.state;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/18 16:12
 * @Description: 模拟block状态
 */
public class ThreadState4 {
    public static void main(String[] args) throws InterruptedException {
        BlockThread t1 = new BlockThread();
        BlockThread t2 = new BlockThread();

        t1.start();
        t2.start();

        Thread.sleep(1000);// 休眠1s，让t1先获取到锁

        System.out.println("t1线程的状态为：" + t1.getState());// TIMED_WAITING
        System.out.println("t2线程的状态为：" + t2.getState());// block
        System.exit(0);
    }

}

class BlockThread  extends Thread{
    @Override
    public void run() {
        commonResource();
    }

    // 同步方法
    public static synchronized void commonResource() {
        while(true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程运行中~~~~~~");
        }
    }
}
