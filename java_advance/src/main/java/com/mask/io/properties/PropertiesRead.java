package com.mask.io.properties;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author: Mask.m
 * @create: 2022/01/09 13:22
 * @description:
 */
public class PropertiesRead {
    public static void main(String[] args) throws Exception{
        String path = "D:\\workspace\\work_demo\\java_advance\\src\\main\\resources\\mysql.properties";
        Properties properties = new Properties();
        // 加载properties文件
        properties.load(new FileInputStream(path));
        // 使用标准输出流 打到控制台 所有的k-v
        properties.list(System.out);

        // 获取单个属性
        System.out.println(properties.get("ip"));

        // 添加属性
        properties.setProperty("database","test");
        // 修改属性
        properties.setProperty("pwd","root1213");

        properties.list(System.out);


    }

    /**
     * 把properties写入到配置文件
     */
    @Test
    public void test() throws Exception{
        String path = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\properties\\mysql.properties";

        Properties properties = new Properties();

        properties.setProperty("name","张三");
        properties.setProperty("age","18");

        // comments -> 注释
        properties.store(new PrintWriter(path),null);

    }
}
