package com.example.work;

import java.util.ArrayList;

/**
 * @author: Mask.m
 * @create: 2021/01/20 19:37
 * @description:
 */

/*
编写代码，实现：自定义一个链表数据结构（可以是单链表或双向链表），
然后初始化一个链表数据，并对该链表实现两两翻转（是翻转整个节点，而不是仅交换节点的值），
然后输出翻转之后的结果。比如构造的链表是：1->2->3->4->5，翻转之后，输出：2->1->4->3->5.
要求：1.要有测试代码和运行结果 2.可以使用IDEA 3.api可以百度但不能抄代码
 */
public class MyTest {

    public static void main(String[] args) {

        ArrayList<MyNode<String>> list = new ArrayList<>();



        MyNode<String> node1 = new MyNode<>(null,"1",null);

        MyNode<String> node2 = new MyNode<String>(null,"2",null);
        MyNode<String> node3 = new MyNode<String>(null,"3",null);
        MyNode<String> node4 = new MyNode<String>(null,"4",null);
        MyNode<String> node5 = new MyNode<String>(null,"5",null);



        node1.setNext(node2);
        node2.setPrev(node1);

        node2.setNext(node3);
        node3.setPrev(node2);
        node3.setNext(node4);

        node4.setPrev(node3);
        node4.setNext(node4);
        node5.setPrev(node4);

        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);


        for (int i = 1; i <= list.size(); i++) {

            if (i == list.size()){
                continue;
            }

            if (i % 2 != 0){
                String temp = list.get(i).item;
                list.get(i).item = list.get(i+1).item;
                list.get(i+1).item = temp;

            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).item);
        }




    }



}
