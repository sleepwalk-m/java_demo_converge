package com.mask.collection.list;

/**
 * @author Mask.m
 * @version 1.0
 * @date 2021/12/13 14:44
 * @Description: 模拟双向链表
 */
public class LinkedList01 {
    public static void main(String[] args) {
        // 创建3个节点
        Node jack = new Node("jack");
        Node tom = new Node("tom");
        Node lisa = new Node("lisa");

        // 将节点关系建立起来
        jack.next = tom;
        tom.next = lisa;
        tom.pre = jack;
        lisa.pre = tom;

        // 头节点和尾节点指向
        Node first = jack;
        Node last = lisa;

        System.out.println("===============正序遍历==================");
        // 正向遍历
        while (true) {
            // 当头节点指向链表的尾部 那么next就等于null了
            if (first == null) {
                break;
            }
            System.out.println(first);
            first = first.next;
        }


        System.out.println("===============倒序遍历==================");
        // 倒序遍历
        while (true) {
            // 当尾节点指向链表的头部 那么next就等于null了
            if (last == null) {
                break;
            }
            System.out.println(last);
            last = last.pre;
        }


        // 新增一个节点
        Node node = new Node("node");

        tom.next = node;
        lisa.pre = node;
        node.pre = tom;
        node.next = lisa;

        System.out.println("===============新增节点正序遍历==================");
        first = jack;
        last = lisa;
        // 正向遍历
        while (true) {
            // 当头节点指向链表的尾部 那么next就等于null了
            if (first == null) {
                break;
            }
            System.out.println(first);
            first = first.next;
        }


    }


}


// 定义一个类 表示双向 链表的一个结点
class Node {
    public Object item;// 真正存放数据
    public Node next;// 下一个节点
    public Node pre;// 前一个节点

    public Node(Object name) {
        this.item = name;
    }

    @Override
    public String toString() {
        return "Node name = " + item;
    }
}
