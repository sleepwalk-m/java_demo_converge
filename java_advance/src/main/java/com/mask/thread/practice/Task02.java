package com.mask.thread.practice;

/**
 * @author: Mask.m
 * @date: 2022/1/18 21:27
 * @version: v1.0
 * @Description: 作业2
 */

/**
 * (1)有2个用户分别从同一个卡上取钱（总额：10000）
 * (2) 每次都取1000, 当余额不足时，就不能取款了
 * (3) 不能出现超取现象 =》
 * 线程同步问题.
 */
public class Task02 {
    public static void main(String[] args) {
        User user = new User();

        Thread t1 = new Thread(user, "小张");
        Thread t2 = new Thread(user, "小王");
        t1.start();
        t2.start();

    }
}


class User implements Runnable{

    private static int MONEY = 50000;

    @Override
    public void run() {
        takeMoney();
    }

    private  void takeMoney() {
        while (true){

            synchronized(this) {
                if (MONEY < 1) {
                    System.out.println("目前余额为：" + MONEY + "，已经不能取钱了");
                    break;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "取了1000元，余额为：" + (MONEY -= 1000));
            }
        }
    }
}