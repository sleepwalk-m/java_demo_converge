package com.mask.collection.set;

/**
 * @author: Mask.m
 * @create: 2021/12/18 13:54
 * @description: 模拟 数组+链表结构 hasheset底层是hashMap
 */
@SuppressWarnings("all")
public class HashSet02 {
    public static void main(String[] args) {

        // 创建数组
        Node[] table = new Node[16];

        // 模拟一个数组+链表的结构
        Node jack = new Node("jack", null);
        table[2] = jack;
        Node mark = new Node("mark", null);
        jack.next = mark;
        Node lucy = new Node("lucy", null);
        mark.next = lucy;

        System.out.println("table = " + table);

    }
}

class Node {
    Object item;
    Node next;

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }
}
