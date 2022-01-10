package com.mask.io.reader;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author: Mask.m
 * @create: 2022/01/08 23:03
 * @description:
 */
public class BufferedReader_ {



    @Test
    public void test() throws Exception{

        String filePath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\reader\\a.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
        }

        // 这里的close 其实也就是关了上面的filereader
        bufferedReader.close();
    }
}
