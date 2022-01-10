package com.mask.collection.list;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author: Mask.m
 * @create: 2021/12/18 12:21
 * @description: linkedlist源码阅读
 */
public class LinkedListCRUD {
    public static void main(String[] args) {

        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);

        System.out.println("list = " + list);

        // 删除
        //list.remove();
        //list.remove(1);
        System.out.println("list = " + list);


        // 修改
        list.set(1,999);
        System.out.println("list = " + list);


        // 遍历
        for (Object o : list) {
            System.out.println("o = " + o);
        }
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println("iterator = " + iterator.next());
        }

    }
}