package com.mask.net_program.util;

import java.io.*;

/**
 * @author: Mask.m
 * @create: 2022/01/25 22:09
 * @description: 工具类
 */
public class StreamUtils {

    /**
     * 将子节流转换为字节数组 方便文件保存
     * @param is
     * @return
     * @throws Exception
     */
    public static byte[] streamToByteArray(InputStream is) throws Exception{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        BufferedInputStream bis = new BufferedInputStream(is);
        int len;
        while ((len = bis.read()) != -1){
            bos.write(bytes,0,len);
        }

        bis.close();
        return bos.toByteArray();
    }

    /**
     * 将字节流转为字符串
     * @param is
     * @return
     * @throws Exception
     */
    public static String streamToString(InputStream is) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }
        reader.close();
        return builder.toString();
    }
}
