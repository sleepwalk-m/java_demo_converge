package com.mask.collection.practice;

/**
 * @author: Mask.m
 * @create: 2022/01/06 21:07
 * @description: 练习一
 */

import cn.hutool.core.util.StrUtil;
import com.mask.collection.entity.News;

import java.util.ArrayList;

/**
 * 1.编程题
 * 按要求实现：
 * (1)封装一个新闻类，包含标题和内容属性，提供get、set方法，重写toString方法，打印对象
 * 时只打印标题;
 * •(2)只提供
 * 个带参数的构造器，实例化对象时，只初始化标题；并且实例化两个对象：
 * 新闻一：新冠确诊病例超干万，数百万印度教信徒赴恒河“圣浴”引民众担忧
 * 新闻二：男子突然想起2个月前钓的鱼还在网兜里，捞起一看赶紧放生
 * (3)将新闻对象添加到ArrayList集合中，并且进行倒序遍历;
 * (4) 在遍历集合过程中，对新闻标题进行处理，超过15字的只保..
 * (5) 在控制台打印遍历出经过处理的新闻标题;
 */
public class Practice1 {
    public static void main(String[] args) {

        News news1 = new News("新冠确诊病例超干万，数百万印度教信徒赴恒河“圣浴”引民众担忧");
        News news2 = new News("男子突然想起2个月前钓的鱼还在网兜里，捞起一看赶紧放生");

        ArrayList<News> list = new ArrayList<>();
        list.add(news1);
        list.add(news2);

        // 3. 倒序遍历
        for (int i = list.size() - 1; i >= 0; i--) {
            // 4. 处理字符串
            if (StrUtil.length(list.get(i).getTitle()) > 15){
                String str = list.get(i).getTitle().substring(0,15) + "...";
                System.out.println("i = " + str);
            }
        }

        //试分析HashSet和TreeSet分别如何实现去重的

    }
}
