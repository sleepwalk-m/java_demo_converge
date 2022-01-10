package com.mask.io.outputstream;

import java.io.*;

/**
 * @author: Mask.m
 * @create: 2022/01/09 12:12
 * @description: 对象操作流
 */
public class ObjectOutputStream_ {
    public static void main(String[] args) throws IOException {

        String path = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\outputstream\\对象操作流写出的文件.dat";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));

        objectOutputStream.write(100); // int -> Integer -> 实现了serializable接口
        objectOutputStream.writeBoolean(true);
        objectOutputStream.writeChar('H');
        objectOutputStream.writeFloat(1.1F);
        objectOutputStream.writeUTF("测试文本");


        // 写创建的对象
        objectOutputStream.writeObject(new Dog("旺财",10));

        objectOutputStream.close();
        System.out.println("对象流写入成功");
    }
}
// 必须实现 Serializable 接口 进行序列化反序列化
class Dog implements Serializable{
    // 建议写死
    private static final long serialVersionUID = 100;


    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
