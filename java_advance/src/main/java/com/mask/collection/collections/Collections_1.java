package com.mask.collection.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: Mask.m
 * @create: 2022/01/06 20:09
 * @description:
 */
public class Collections_1 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();


        list.add("jack");
        list.add("tom");
        list.add("smith");
        list.add("uo");
        list.add("uo");

        // 计算某个元素出现的次数
        int uo = Collections.frequency(list, "uo");
        System.out.println("uo出现的次数 = " + uo);

        // 反转集合元素
        Collections.reverse(list);
        System.out.println("反转后的list=" + list);

        // 打乱元素 比如抽奖的场景
        for (int i = 1; i <= 5; i++) {
            Collections.shuffle(list);
            System.out.println("打乱次数 " + i + "，打乱后的list = " + list);
        }

        // 自然排序
        Collections.sort(list);
        System.out.println("自然排序后的list = " + list);

        // 比较器排序
        //Collections.sort(list, Comparator.comparing(String::length));
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length()-o1.length();
            }
        });
        System.out.println("比较器排序后的list = " + list);


        // 交换元素
        System.out.println("交换之前的list =" + list);
        Collections.swap(list,0,1);
        System.out.println("交换之后的list =" + list);
    }
}
