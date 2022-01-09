package com.mask.io.outputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: Mask.m
 * @create: 2022/01/08 20:47
 * @description: 演示文件复制 用一张图片
 */
public class FileCopy {
    public static void main(String[] args) throws IOException {

        String inputPath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\inputstream\\读的图片.png";
        String outputPath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\outputstream\\复制后的图片.png";

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(inputPath);
            fileOutputStream = new FileOutputStream(outputPath);

            // 开始读 用字节数组来读
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1){
                fileOutputStream.write(bytes,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileOutputStream.close();
            fileInputStream.close();
        }


    }
}
