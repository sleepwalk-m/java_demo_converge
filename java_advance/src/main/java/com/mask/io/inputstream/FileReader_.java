package com.mask.io.inputstream;

import org.junit.Test;

import java.io.*;

/**
 * @author: Mask.m
 * @create: 2022/01/08 20:08
 * @description: 字节输入流
 */
public class FileReader_ {
    public static void main(String[] args) {




    }


    /**
     * filereader 读取
     * 1个字符 + 数组读
     */
    @Test
    public void test1() throws IOException {

        String filePath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\inputstream\\hello.txt";

        FileReader fileReader = new FileReader(filePath);

        // 1. 读单个
        //int data;
        /*while ((len = fileReader.read()) != -1){
            System.out.print((char)data);
        }*/

        // 2. 用byte数组
        char[] chars = new char[8];
        int readLen;
        while ((readLen = fileReader.read(chars)) != -1){
            System.out.print(new String(chars,0,readLen));
        }


        fileReader.close();
    }

}
