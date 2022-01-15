package com.mask.reflex.clazz;

import com.mask.reflex.entity.Car;

import java.lang.reflect.Field;

/**
 * @author: Mask.m
 * @create: 2022/01/09 20:49
 * @description: 演示class类的常用方法
 */
public class Class02 {
    public static void main(String[] args) throws Exception{
        Class<?> clazz = Class.forName("com.mask.reflex.entity.Car");

        //1. 显示该clazz是哪个类的class对象
        System.out.println("clazz = " + clazz);

        //2. 运行类型
        System.out.println(clazz.getClass());

        //3. 获取包名
        System.out.println(clazz.getPackage().getName());

        //4. 获取全类名
        System.out.println(clazz.getName());

        //5. 反射创建对象
        Car car = (Car) clazz.newInstance();
        //6. 获取属性（public） private需要取消检查
        Field brand = clazz.getField("brand");
        System.out.println("brand = " + brand.getName());

        // 7. 获取所有的属性 public的
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println("field = " + field.getName());
        }

    }
}
