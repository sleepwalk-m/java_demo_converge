package com.mask.collection.map;

import java.util.Hashtable;

/**
 * @author: Mask.m
 * @create: 2022/01/04 20:27
 * @description: hashtable演示
 */
public class HashTable_ {
    public static void main(String[] args) {

        Hashtable table = new Hashtable();//ok
        table.put("john", 100); //ok
        //table.put(null, 100); //异常  key不能为null
        //table.put("john", null);//异常 value不能为null
        table.put("lucy", 100);//ok
        table.put("lic", 100);//ok
        table.put("lic", 88);//替换
        table.put("s1", 88);
        table.put("s2", 88);
        table.put("s3", 88);
        table.put("s4", 88);
        table.put("s5", 88);
        table.put("s6", 88);
        System.out.println(table);
    }
}
