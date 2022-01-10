package com.mask.collection.set;

import java.util.LinkedHashSet;

/**
 * @author: Mask.m
 * @create: 2021/12/19 12:42
 * @description:
 */
public class LinkedHashSet01 {
    public static void main(String[] args) {
        LinkedHashSet set = new LinkedHashSet();
//        set.add("jack");
//        set.add("mary");
//        set.add("mark");
//        set.add("jack");

        System.out.println("set = " + set);


        // 验证linkedHashSet的扩容 和 转树
        // 结论： 扩容跟hashmap一样，0.75扩容因子，2倍扩容
        for (int i = 1; i <= 12; i++) {
            set.add(new User1(i+""));
        }
    }
}


class User1{
    private String name;

    public User1(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 200;
    }
}
