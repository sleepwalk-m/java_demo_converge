package com.mask.io.outputstream;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: Mask.m
 * @create: 2022/01/08 20:08
 * @description: 字节输入流
 */
public class FileWriter_ {
    public static void main(String[] args) {




    }


    /**
     * filereader 读取
     * 1个字符 + 数组读
     */
    @Test
    public void test1() throws IOException {

        String filePath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\outputstream\\hello.txt";

        FileWriter fileWriter = new FileWriter(filePath);

        // 字符串写
        //fileWriter.write("这是一个filewriter写入的内容 请问写出去了吗");

        // 数组写
        String str = "这是一个filewriter写入的内";
        fileWriter.write(str.toCharArray(),0,str.length());


        // 必须刷新或者关流才能写入成功
        fileWriter.flush();
        fileWriter.close();
    }

}
