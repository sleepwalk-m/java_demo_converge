package com.example.work;

/**
 * @author: Mask.m
 * @create: 2021/01/21 10:35
 * @description:
 */
public class Test {


    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int data) {
            value = data;
        }

        public ListNode() {
        }
    }

    // 创建链表
    public static ListNode createLink(int arr[]) {
        // 链表头，起始的第一个节点
        ListNode head = null;
        // 当前链表
        ListNode p = null;
        // 链表尾部
        ListNode tail = null;
        for (int i = 0; i < arr.length; i++) {
            // 遍历创建链表节点对象
            // 当前链表，空链表，其实就是拿到每一次的新的一个链表节点
            p = new ListNode();
            // 设置值进去
            p.value = arr[i];

            // 尾指针的指向暂时是null
            p.next = null;

            //第一次进 是把head作为链表第一个节点，如果是null，就生成第一个
            if (head == null) {
                // 把起始节点设置为当前临时节点
                head = p;
                // 尾节点也暂时设置为当前节点
                tail = p;
            }
            // 尾节点 第一次进来的时候，其实就是建立起来了一个链接的关系，这里虽然是操作的
            tail = tail.next = p;
        }
        // 返回的是整个链表
        return head;
    }

    // 输出链表
    public static void printLink(ListNode head) {
        for (ListNode p = head; p != null; p = p.next) {
            System.out.print(p.value + " ");
        }
        System.out.println();
    }

    // 两两翻转链表
    public static ListNode filpList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode p = null, q = null, pre = null;
        for (p = head; p != null && p.next != null; p = p.next) {
            q = p.next;
            if (p == head) {
                p.next = q.next;
                q.next = p;
                head = q;
                pre = p;
            } else {
                pre.next = q;
                p.next = q.next;
                q.next = p;
                pre = p;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 8, 4};
        ListNode head = createLink(arr);
        printLink(head);
        head = filpList(head);
        printLink(head);

    }
}


