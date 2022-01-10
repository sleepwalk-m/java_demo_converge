package com.mask.io.transformation;

import org.junit.Test;

import java.io.*;

/**
 * @author: Mask.m
 * @create: 2022/01/09 12:50
 * @description: 演示乱码 转换流解决
 */
public class CodeQuestion {
    public static void main(String[] args) throws Exception{
        String filePath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\transformation\\a.txt";

        //BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        // a.txt 是ansi编码 所以读取会乱码 hello��˳ƽ
        //String data;
        //while ((data = bufferedReader.readLine()) != null){
         //   System.out.println(data);
        //}
        //bufferedReader.close();

        // 现在用转换流解决 指定字符编码GBK 就可以解决了
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath),"GBK");
        char[] chars = new char[1024];
        int len;
        while ((len = inputStreamReader.read(chars)) != -1){
            System.out.println(new String(chars,0,len));
        }

        inputStreamReader.close();
    }


    /**
     * ouputstreamReader 转换流
     */
    @Test
    public void test() throws Exception{
        String filePath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\transformation\\b.txt";
        // 默认本机编码 是UTF-8写出的 那么文件里面就会乱码 ɵ�������hello
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(filePath),"GBK");
        outputStreamWriter.write("傻逼是你把hello");
        outputStreamWriter.flush();
        outputStreamWriter.close();
    }
}
