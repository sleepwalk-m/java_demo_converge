package com.mask.thread.state;

import jdk.nashorn.internal.ir.IfNode;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/18 17:36
 * @Description: 死锁演示
 */
public class DeadLockDemo {
    public static void main(String[] args) {

        DeadThread t1 = new DeadThread(true);
        DeadThread t2 = new DeadThread(false);

        t1.start();
        t2.start();

        /*

        控制台输出：
            此时获取的锁是o1,flag是true
            此时获取的锁是o2,flag是false

                当为true时，t1获取到了o1的锁，此时继续往下获取o2的锁 获取不到 已经被false的情况占用
                当为false时，t2获取到了o2的锁，此时继续往下获取o1的锁 获取不到 已经被true的情况占用

                则造成死锁了，互相在等待对方释放锁 程序就会卡在这里
         */
    }
}


class DeadThread extends Thread{
    private static Object o1 = new Object();
    private static Object o2 = new Object();


    private boolean flag;

    public DeadThread(boolean flag){
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag){
            synchronized (o1){
                System.out.println("此时获取的锁是o1,flag是" + flag);
                synchronized (o2){
                    System.out.println("此时获取的锁是o2,flag是" + flag);
                }
            }
        }else {
            synchronized (o2){
                System.out.println("此时获取的锁是o2,flag是" + flag);
                synchronized (o1){
                    System.out.println("此时获取的锁是o1,flag是" + flag);
                }
            }
        }
    }
}
