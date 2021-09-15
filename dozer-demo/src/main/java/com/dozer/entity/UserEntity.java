package com.dozer.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: Mask.m
 * @create: 2021/09/15 21:08
 * @description: pojo
 */
@Data
public class UserEntity {

    private String id;
    private String name;
    private int age;
    private String address;
    private Date birthday;
}
