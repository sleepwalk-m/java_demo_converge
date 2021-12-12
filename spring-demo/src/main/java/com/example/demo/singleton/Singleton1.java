package com.example.demo.singleton;

/**
 * @author: Mask.m
 * @create: 2021/01/12 11:33
 * @description: 手写单例模式  饿汉式  类一加载就加载
 */
public class Singleton1 {
    /**
     * 1.私有化构造方法
     */
    private Singleton1(){}

    // 写一个私有的成员变量
    private static final Singleton1 INSTANCE = new Singleton1();


    /**
     * 2.对外提供公共的静态方法，返回值就是唯一实例
     */
    public static Singleton1 getInstance(){
        return INSTANCE;
    }
}
