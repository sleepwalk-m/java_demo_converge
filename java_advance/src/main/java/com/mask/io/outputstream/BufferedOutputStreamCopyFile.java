package com.mask.io.outputstream;

import java.io.*;

/**
 * @author: Mask.m
 * @create: 2022/01/08 23:51
 * @description: 缓冲字节流 拷贝文件
 */
public class BufferedOutputStreamCopyFile {

    public static void main(String[] args) throws IOException {

        String filePath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\outputstream\\复制后的图片.png";
        String copyPath = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\outputstream\\缓冲字节流复制后的图片.png";

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(copyPath));

        byte[] bytes = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(bytes)) != -1){
            bufferedOutputStream.write(bytes,0,len);
        }

        bufferedInputStream.close();
        bufferedOutputStream.close();

    }

}
