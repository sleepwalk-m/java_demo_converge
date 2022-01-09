package com.mask.io.writer;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * @author: Mask.m
 * @create: 2022/01/08 23:44
 * @description:
 */
public class BufferedWriter_ {

    @Test
    public void test() throws Exception{

        String filePath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\writer\\hello.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

        bufferedWriter.write("如果超人会飞");
        bufferedWriter.newLine();
        bufferedWriter.write("那就请你在空中停一停歇");

        bufferedWriter.flush();
        bufferedWriter.close();

    }
}
