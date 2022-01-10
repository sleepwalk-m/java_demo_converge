package com.mask.collection.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: Mask.m
 * @create: 2021/12/18 13:54
 * @description:
 */
public class HashSet01 {
    public static void main(String[] args) {
        Set set = new HashSet();

        set.add("jack");
        set.add("java");
        set.add("jack"); // 重试
        set.add("mask");
        set.add(null);
        set.add(null);// 多个null

        System.out.println("set = " + set);



        // 遍历
        System.out.println("== =======迭代器遍历==========");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println("next = " + next);
        }

        System.out.println("=========增强For遍历==========");
        for (Object o : set) {
            System.out.println("o = " + o);
        }
    }
}
