package com.mask.filetransport;

import com.mask.filetransport.util.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author: Mask.m
 * @create: 2022/02/02 18:12
 * @description: 测试断点续传
 */
public class RandomAccessFileTest {


    @Test
    public void testSingal() throws Exception{
        String filePath = "D:\\test.mp4";
        String file = "E:\\test.mp4";

        // 单线程 4.5G大小的文件 耗时18844ms
        FileInputStream fis = new FileInputStream(new File(filePath));
        FileOutputStream fos = new FileOutputStream(new File(file));
        byte[] bytes = new byte[1024 * 8];

        long begin = System.currentTimeMillis();
        int len;
        while ((len = fis.read(bytes)) != -1){
            fos.write(bytes,0,len);
        }
        long end = System.currentTimeMillis();
        System.out.println("单线程传文件耗时：" + (end -begin) + "ms");
        fis.close();
        fos.close();
    }


    @Test
    public void testThread() throws Exception{
        String src = "D:\\test.mp4";
        String dest = "E:\\test.mp4";

        // 多线程 4.5G大文件耗时 ms
        FileUtils.transportFile(src,dest);
    }



}
