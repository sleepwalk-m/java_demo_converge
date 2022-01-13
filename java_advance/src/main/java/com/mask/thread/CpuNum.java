package com.mask.thread;

/**
 * @author: Mask.m
 * @create: 2022/01/12 23:00
 * @description: 获取机器cpu核心数
 */
public class CpuNum {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int i = runtime.availableProcessors();
        System.out.println("机器的cpu核心数 = " + i);// 我的机器是8核16线程  这里输出是16线程
    }
}
