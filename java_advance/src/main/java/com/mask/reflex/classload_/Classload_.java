package com.mask.reflex.classload_;

import com.mask.reflex.entity.Dog;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * @author: Mask.m
 * @create: 2022/01/12 22:30
 * @description: 演示静态加载和动态加载
 */
public class Classload_ {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        switch (next) {
            case "1":
                //People people = new People();// 静态加载 编译时就去加载该类 然后报错编译不通过
                //people.doCry();
                break;
            case "2":
                Class<?> clazz = Class.forName("People");// 动态加载，只有当输入2的时候，也就是走到这里才去加载，不走到这里就不会影响整体代码
                Object o = clazz.newInstance();
                Method cry = clazz.getMethod("cry");
                cry.invoke(o);
                System.out.println("ok");
                break;
            default:
                System.out.println("结束");
                break;
        }


    }
}
