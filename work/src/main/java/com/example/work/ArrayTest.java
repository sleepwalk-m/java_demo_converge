package com.example.work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Mask.m
 * @create: 2021/04/01 22:35
 * @description: 测试
 */
public class ArrayTest {

    public static void main(String[] args) {

       /* int[] arr = {1, 32, 5, 2, 16};
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

        }

        System.out.println(Arrays.toString(arr));*/

       String a = "http://192.168.200.130/";
        List<String> list = new ArrayList<>();
        list.add("http://192.168.200.130/group1/xxx/xx1.jpg");
        list.add("http://192.168.200.130/group1/xxx/xx2.jpg");
        list.add("http://192.168.200.130/group1/xxx/xx3.jpg");
        list.add("http://192.168.200.130/group1/xxx/xx4.jpg");


        List<String> collect = list.stream().map(s -> s.replace(a, "")).collect(Collectors.toList());

        System.out.println(collect.toString());

    }
}
