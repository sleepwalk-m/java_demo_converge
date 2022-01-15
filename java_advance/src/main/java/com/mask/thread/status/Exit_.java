package com.mask.thread.status;

/**
 * @author: Mask.m
 * @date: 2022/1/15 22:46
 * @version: v1.0
 * @Description: 测试线程中断
 */
public class Exit_ {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.start();

        // 当需要线程中止时，将变量设为false
        Thread.sleep(10 * 1000);
        t.setFlag(false);
    }
}


class T extends Thread{
    // 定义一个变量控制
    private boolean flag = true;
    int count = 0;
    @Override
    public void run() {
        while (flag){
            try {
                Thread.sleep(50);
                System.out.println(Thread.currentThread().getName() + "运行了" + ++count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
