package com.mask.thread.state;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/18 14:16
 * @Description: 线程状态
 */
public class ThreadState {
    public static void main(String[] args) throws InterruptedException {

        TState1 tState1 = new TState1();
        // 初始状态：NEW
        System.out.println(tState1.getName() + " 状态 " + tState1.getState());
        tState1.start();// 启动之后：RUNNABLE

        // 当sleep的时候：TIMED_WAITING
        while (tState1.getState() != Thread.State.TERMINATED) {
            System.out.println(tState1.getName() + " 状态 " + tState1.getState());
            Thread.sleep(500);
        }

        System.out.println(tState1.getName() + " 状态 " + tState1.getState());
    }
}

class TState1 extends Thread{
    @Override
    public void run() {
        while (true){
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("hi #######" + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
    }
}

