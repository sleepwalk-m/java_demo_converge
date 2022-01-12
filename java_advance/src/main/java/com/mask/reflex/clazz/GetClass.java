package com.mask.reflex.clazz;

import com.mask.reflex.entity.Car;

import java.io.InputStream;

/**
 * @author: Mask.m
 * @create: 2022/01/12 21:54
 * @description: 演示得到class对象的几种方式
 */
public class GetClass {
    public static void main(String[] args) throws Exception{

        // 1. class.forName 例如spring.factorys配置文件 就是这样的
        Class.forName("com.mask.reflex.entity.Car");

        // 2. 类加载阶段 类名.class 应用场景：参数传递
        Class<Car> carClass = Car.class;
        System.out.println(String.class);

        // 3. 运行阶段，对象.getClass
        Car car = new Car();
        Class<? extends Car> aClass = car.getClass();
        System.out.println("aClass = " + aClass);

        // 4. 通过类加载器获取 其实本质上就是Class.Forname 就是用的类加载器
        Class<?> aClass1 = ClassLoader.getSystemClassLoader().loadClass("com.mask.reflex.entity.Car");
        System.out.println("aClass1 = " + aClass1);

        // 5. 基本数据类型 int char .class
        Class<Integer> integerClass = int.class;
        System.out.println("integerClass = " + integerClass);

        // 6. 基本数据类型的包装类 Integer
        Class<Integer> integerClass1 = Integer.class;
        Class<Integer> type = Integer.TYPE;
        System.out.println("type = " + type);// int 可见.Type拿到的就是基本数据类型
        System.out.println("integerClass1 = " + integerClass1);// class java.lang.Integer
    }
}
