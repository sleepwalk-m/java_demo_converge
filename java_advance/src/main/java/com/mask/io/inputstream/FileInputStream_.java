package com.mask.io.inputstream;

import org.junit.Test;

import java.io.*;

/**
 * @author: Mask.m
 * @create: 2022/01/08 20:08
 * @description: 字节输入流
 */
public class FileInputStream_ {
    public static void main(String[] args) {




    }


    /**
     * fileinputstream 读取
     * 1个字节 + 数组读
     */
    @Test
    public void test1() throws IOException {

        String filePath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\hello.txt";

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        // 1. 单个字节读取
        /*int len;
        // 返回的是当前字节的assii码值
        while ((len = fileInputStream.read()) != -1){
            System.out.print((char)len);
        }*/

        // 2. 数组读取
        int modCount = 0;
        byte[] bytes = new byte[8]; // 一次读8个字节
        int length; // 读取的字节长度
        // 返回的是读取的字节长度
        while ((length = fileInputStream.read(bytes)) != -1){
            modCount++;
            System.out.print(new String(bytes,0,length));
        }
        System.out.println("字节数组读取的次数为：" + modCount);


        fileInputStream.close();

    }

    /**
     * fileinputstream 读取
     * 1个字节 + 数组读
     */
    @Test
    public void test2() throws IOException {

        String filePath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\inputstream\\hello.txt";

        // 参数跟true,代表追加写 否则是替换 并且没有该文件的话会自动创建
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));

        // 写一个字符




        fileOutputStream.close();

    }
}
