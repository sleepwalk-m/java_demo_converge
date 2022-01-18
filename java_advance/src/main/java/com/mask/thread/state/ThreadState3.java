package com.mask.thread.state;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/18 16:12
 * @Description: 模拟waiting状态
 */
public class ThreadState3 {
    public static void main(String[] args) throws InterruptedException {
        Thread main = Thread.currentThread();

        Thread thread2 = new Thread(() -> {
            for (int i = 1;i <= 10;i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("子线程运行中~~~" + i);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("此时main线程的状态为：" + main.getState());
            }
        });
        thread2.start();
        thread2.join();// join方法 main线程此时是waiting状态

        System.out.println("最终main线程的状态为：" + main.getState());
    }

}
