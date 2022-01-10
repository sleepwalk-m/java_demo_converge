package com.mask.io.writer;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author: Mask.m
 * @create: 2022/01/08 23:47
 * @description:
 */
public class BufferedWriterCopyFile {

    @Test
    public void test() throws Exception{

        String filePath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\writer\\hello.txt";
        String copyPath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\writer\\复制后的.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(copyPath));

        String line;
        while ((line = bufferedReader.readLine()) != null){
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();

    }
}
