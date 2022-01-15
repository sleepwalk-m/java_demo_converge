package com.mask.thread.method;

/**
 * @author: Mask.m
 * @date: 2022/1/15 23:03
 * @version: v1.0
 * @Description: 线程常用方法
 */
public class ThreadMethod01 {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.setName("老韩"); // 设置线程名字
        t.setPriority(Thread.MIN_PRIORITY);// 设置线程优先级
        t.start();

        // 提前中止t线程
        // 主线程打印5个hi，就中断t线程
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "hi~~~~" + i);
        }
        int priority = t.getPriority();// 获取优先级
        System.out.println("priority = " + priority);
        // 当走到这里，就会中断t线程的休眠
        t.interrupt();
    }
}


class T extends Thread{

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "吃包子！！！" + i);
            }

            try {
                System.out.println(Thread.currentThread().getName() + "休眠中~~~");
                Thread.sleep(20*1000);
            }catch (InterruptedException e){
                // 睡眠中的线程被中断 注意不是中止
                System.out.println(Thread.currentThread().getName() + "线程被中断~~~~~");
            }
        }
    }
}
