package com.mask.service;

import lombok.Data;

/**
 * @author: Mask.m
 * @create: 2021/09/11 18:50
 * @description: 服务类
 */
@Data
public class HelloService {

    private String name;
    private String address;

    public HelloService(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String sayHello(){
        return "你好！我的名字叫 " + name + "，我来自 " + address;
    }
}
