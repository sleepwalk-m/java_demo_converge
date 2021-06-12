package com.example.work;

import java.util.Arrays;

/**
 * @author: Mask.m
 * @create: 2021/04/01 22:35
 * @description: 测试
 */
public class ArrayTest {

    public static void main(String[] args) {

        int[] arr = {1, 32, 5, 2, 16};
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

        System.out.println(Arrays.toString(arr));

    }
}
