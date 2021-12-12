package com.example.demo.singleton;

/**
 * @author: Mask.m
 * @create: 2021/01/12 11:33
 * @description: 手写单例模式  懒加载 懒汉式 第一次获取的时候才去加载 DCL  double check lock
 */
public class Singleton2 {
    /**
     * 1.私有化构造方法
     */
    private Singleton2(){}

    // 写一个私有的成员变量  volatile 禁止指令重排
    private static volatile Singleton2 instance = null;


    /**
     * 2.对外提供公共的静态方法，返回值就是唯一实例
     */
    public static Singleton2 getInstance(){

        // 只让在第一次实例化的时候 才让线程安全 保证效率
        if (instance == null) {
            // 3. 如果是null，则表示是第一次来获取
            // 加锁：保证在多线程环境下代码只能有一个线程在操作
            synchronized (Singleton2.class) {
                // 保证instance只被创建一次
                if (instance == null){
                    instance = new Singleton2();
                }
            }
        }


        return instance;
    }
}
