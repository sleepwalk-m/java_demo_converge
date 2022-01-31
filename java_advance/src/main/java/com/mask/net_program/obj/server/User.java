package com.mask.net_program.obj.server;

import java.io.Serializable;

/**
 * @author: Mask.m
 * @create: 2022/01/31 13:51
 * @description:
 */
public class User implements Serializable {

    private static final long serialVersionUID = 110L;

    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
