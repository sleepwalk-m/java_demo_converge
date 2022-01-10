package com.mask.collection.set;

import java.util.HashSet;

/**
 * @author: Mask.m
 * @create: 2021/12/18 20:38
 * @description: Hashset源码阅读
 */
public class HashSet03  {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<String>();

        /*set.add("java");
        set.add("php");
        set.add("typescript");*/
        for (int i = 1; i <= 100; i++) {
            set.add(String.valueOf(i));
        }

        System.out.println("set = " + set);

    }
}
