package com.example.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/08/30 22:16
 * @description: 测试集合排序 compareto接口的实现原理
 */
public class ListSortDemo {
    public static void main(String[] args) {
        User user1 = new User("张三", 50);
        User user2 = new User("李四", 23);
        User user3 = new User("王五", 88);

        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);

        // 源码没看懂 有结论： o1 是新数据，o2是上一个数据，当新数据 > 老数据，则是正序排序 ，当新数据 < 老数据，则是倒序排序
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() > o2.getAge() ? 1 : -1;
            }
        });

        System.out.println(list);
    }
}
