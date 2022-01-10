package com.mask.collection.set;

import cn.hutool.core.util.StrUtil;
import com.sun.org.apache.regexp.internal.RE;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author: Mask.m
 * @create: 2022/01/04 22:39
 * @description:
 */
public class TreeSet_ {
    public static void main(String[] args) {
        // 默认是字典排序
        TreeSet treeSet1 = new TreeSet();
        // 匿名内部类写法
        TreeSet treeSet2 = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                // 比较字符串大小 按照字典排序的
                //return ((String)o1).compareTo((String)o2);

                // 思考：如果按照字符串长度呢？ 那么abc能加进去吗？
                return ((String)o1).length() - ((String)o2).length();
            }
        });
        // lambda + hutool工具类写法  o2可以理解为下一个数据，o1为上一个数据，o2-o1>0即倒序，o1-o2>0即正序
        /*TreeSet treeSet = new TreeSet((o1, o2) ->
             StrUtil.compare((String)o1,(String)o2,true)
        );*/

        //  lambda方式
        TreeSet treeSet = new TreeSet(Comparator.comparing(String::length));

        treeSet.add("jack");
        treeSet.add("tom");
        treeSet.add("sp");
        treeSet.add("a");

        // 在字符串长度排序的情况下 这个能加入进去吗？ 不能！ 因为排序 这个也是3位长度 已经有3位长度了
        treeSet.add("abc");

        System.out.println("treeSet = " + treeSet);
    }
}
