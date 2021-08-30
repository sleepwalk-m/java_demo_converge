package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Mask.m
 * @create: 2021/08/30 22:15
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;
    private Integer age;
}
