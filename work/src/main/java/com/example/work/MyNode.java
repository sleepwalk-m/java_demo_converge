package com.example.work;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author: Mask.m
 * @create: 2021/01/20 19:16
 * @description:
 */
/*
编写代码，实现：自定义一个链表数据结构（可以是单链表或双向链表），
然后初始化一个链表数据，并对该链表实现两两翻转（是翻转整个节点，而不是仅交换节点的值），
然后输出翻转之后的结果。比如构造的链表是：1->2->3->4->5，翻转之后，输出：2->1->4->3->5.
要求：1.要有测试代码和运行结果 2.可以使用IDEA 3.api可以百度但不能抄代码
 */
public class MyNode<E> {
    E item;
    MyNode<E> next;
    MyNode<E> prev;

    public MyNode(MyNode<E> prev, E element, MyNode<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }

    public E getItem() {
        return item;
    }

    public void setItem(E item) {
        this.item = item;
    }

    public MyNode<E> getNext() {
        return next;
    }

    public void setNext(MyNode<E> next) {
        this.next = next;
    }

    public MyNode<E> getPrev() {
        return prev;
    }

    public void setPrev(MyNode<E> prev) {
        this.prev = prev;
    }
}
