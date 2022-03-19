package com.book;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.*;

/**
 * @author: Mask.m
 * @create: 2022/03/19 00:43
 * @description:
 */
public class MyBookPipeline implements Pipeline {

    private static final String PATH = "D:\\book";

    @Override
    public void process(ResultItems resultItems, Task task){

        String level = resultItems.get("level");
        if (StringUtils.equals(level,"detail")){
            String title = resultItems.get("title");
            String articleTitle = resultItems.get("articleTitle");
            String content = resultItems.get("content");

            // 1. 创建目录
            String dirPath  = PATH +"\\" +title;// d:\\book\圣墟
            File file = new File(dirPath);// 圣墟
            if (!file.isDirectory()){
                file.mkdir();
            }

            // 2. 保存txt
            String articlePath = file.getPath() + "\\" +articleTitle + ".txt";// 章节名.txt
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(new File(articlePath)));
                writer.write(content);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
