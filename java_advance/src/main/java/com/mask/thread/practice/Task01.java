package com.mask.thread.practice;

/**
 * @author: Mask.m
 * @date: 2022/1/18 21:06
 * @version: v1.0
 * @Description: 作业1
 */

import cn.hutool.core.util.RandomUtil;
import jdk.nashorn.internal.ir.WhileNode;

import java.util.Random;
import java.util.Scanner;

/**
 * 1. main方法中启动2个线程
 * 2.在第一个线程中随机打印100以内的整数
 * 3. 直到第2个线程从键盘读取到了q命令，结束程序
 */
public class Task01 {
    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2(t1);

        t1.start();
        t2.start();
    }
}


// 随机打印整数
class T1 extends Thread{
    private boolean loop = true;// 定义一个变量来控制循环

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        while (loop){
            try {
                System.out.println(RandomUtil.randomInt(101));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class T2 extends Thread{
    T1 t1;
    // 定义构造把t1对象传参
    public T2(T1 t1){
        this.t1 = t1;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入Q退出程序");
            char c = scanner.next().toUpperCase().charAt(0);
            if (c == 'Q'){
                t1.setLoop(false);
                System.out.println("T2线程退出");
                break;
            }
        }
    }
}
