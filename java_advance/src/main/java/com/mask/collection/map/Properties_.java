package com.mask.collection.map;

import java.util.Properties;

/**
 * @author: Mask.m
 * @create: 2022/01/04 22:29
 * @description:
 */
public class Properties_ {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("Jack","1");
        properties.put("Java","2");
        properties.put("Php","3");


        System.out.println("properties = " + properties);

        Object java = properties.get("Java");
        System.out.println("java = " + java);

        properties.put("a","B");
        Object a = properties.get("a");
        System.out.println("a = " + a);


    }
}
