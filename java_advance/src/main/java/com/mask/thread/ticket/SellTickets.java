package com.mask.thread.ticket;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/14 18:20
 * @Description: 多线程售票问题 3个窗口手售票100张
 */
public class SellTickets {
    public static void main(String[] args) {

        SellTicket01 sellTicket01 = new SellTicket01();

        Thread t1 = new Thread(sellTicket01);
        Thread t2 = new Thread(sellTicket01);
        Thread t3 = new Thread(sellTicket01);

        t1.start();
        t2.start();
        t3.start();

    }
}


class SellTicket01 implements Runnable{

    private static int ticketsNum = 100;// 卖100张票

    @Override
    public void run() {



            while (true){
                synchronized (this) {
                // 票卖完了
                if (ticketsNum < 0){
                    System.out.println("售票结束.....");
                    break;
                }


                // 模拟延迟50ms
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                // 卖票
                System.out.println("窗口：" + Thread.currentThread().getName() + "  卖出了第" + ticketsNum-- + "张票");
            }
        }


    }
}

