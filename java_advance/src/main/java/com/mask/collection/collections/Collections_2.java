package com.mask.collection.collections;

import java.util.*;

/**
 * @author: Mask.m
 * @create: 2022/01/06 20:09
 * @description:
 */
public class Collections_2 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add("jack");
        list.add("tom");
        list.add("smith");
        list.add("uo");
        list.add("uo");

        // 根据元素自然排序，返回集合中的最大值
        String max = Collections.max(list);
        System.out.println("自然排序max = " + max);// uo

        // 根据元素比较器排序，返回集合中的最大值
        String maxLen = Collections.max(list, Comparator.comparing(String::length));
        System.out.println("比较器排序maxLen = " + maxLen);// smith


        Collections.min(list); // min同理

        List<String> tempList = new ArrayList<>();
        // 为了完成一个copy 需要赋值 避免源码中的报错：Source does not fit in dest
        tempList.addAll(Arrays.asList("1","2","3","4","5"));
        System.out.println("copy前的tempList = " + tempList);
        Collections.copy(tempList,list);
        System.out.println("copy后的tempList = " + tempList);

        // 新值替换旧值
        Collections.replaceAll(list,"uo","欧呦");
        System.out.println("替换后的list = " + list);

    }
}
