package com.stream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Mask.m
 * @create: 2021/10/08 18:22
 * @description: 实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area;  // 地区
}
