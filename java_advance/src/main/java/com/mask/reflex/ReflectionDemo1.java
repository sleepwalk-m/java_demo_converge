package com.mask.reflex;

import com.mask.reflex.entity.Cat;

import java.lang.reflect.Method;

/**
 * @author: Mask.m
 * @create: 2022/01/09 16:47
 * @description:  测试反射调用和普通调用消耗的时间
 */
public class ReflectionDemo1{
    public static void main(String[] args) throws Exception{
        m1(); // 3ms
        m2();// 1058ms setAccessible=true 则 600ms
    }

    // new 对象调用方法耗时
    public static void m1(){
        Cat cat = new Cat();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 900000000; i++) {
            cat.hi();
        }
        long end = System.currentTimeMillis();
        System.out.println("new调用的时间ms = " + (end-begin));

    }

    // 反射 调用方法耗时
    public static void m2() throws Exception{
        Class<?> clazz = Class.forName("com.mask.reflex.entity.Cat");
        Object o = clazz.newInstance();
        long begin = System.currentTimeMillis();
        Method method = clazz.getMethod("hi");
        // method.setAccessible(true); // 取消访问检查 可以提高反射的执行效率
        for (int i = 0; i < 900000000; i++) {
            method.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("反射的时间ms = " + (end-begin));
    }

}
