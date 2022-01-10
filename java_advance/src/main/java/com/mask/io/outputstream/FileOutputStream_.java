package com.mask.io.outputstream;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: Mask.m
 * @create: 2022/01/08 20:08
 * @description: 字节输入流
 */
public class FileOutputStream_ {
    public static void main(String[] args) {




    }


    /**
     * fileOutputStream 写
     * 1个字节 + 数组写
     */
    @Test
    public void test2() throws IOException {

        String filePath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\outputstream\\a.txt";

        // 参数跟true,代表追加写 否则是替换 并且没有该文件的话会自动创建
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath),true);

        // 写一个字符
        //fileOutputStream.write('H');

        // 写一个字符串
        String str = "mask,world!";
        //fileOutputStream.write(str.getBytes());

        // 用数组写
        //fileOutputStream.write(str.getBytes(),0,str.length());

        // 追加写
        fileOutputStream.write("你好，世界".getBytes());


        fileOutputStream.close();

    }
}
