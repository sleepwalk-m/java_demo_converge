package com.mask.reflex.entity;

/**
 * @author: Mask.m
 * @create: 2022/01/09 18:17
 * @description:
 */
public class Cat {

    private String name;
    private String age;

    public Cat() {
    }


    public void hi(){

    }

    public Cat(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
