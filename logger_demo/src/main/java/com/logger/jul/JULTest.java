package com.logger.jul;

import org.junit.Test;

import java.io.InputStream;
import java.util.logging.*;

/**
 * @author: Mask.m
 * @create: 2022/02/03 13:11
 * @description: jul 日志的测试
 */
public class JULTest {

    /**
     * 快速入门
     * @throws Exception
     */
    @Test
    public void testQuick() throws Exception{

        Logger logger = Logger.getLogger("com.logger.jul.JULTest");

        // 记录日志
        logger.info("这是一个info信息");

        // 通用方法
        logger.log(Level.INFO,"这是第2个info信息");

        // 占位符记录变量
        String name = "Test info";
        int age = 20;
        logger.log(Level.INFO,"info 参数信息:{0},{1}",new Object[]{name,age});
    }


    /**
     * 日志级别
     * @throws Exception
     */
    @Test
    public void testLevel() throws Exception{
        Logger logger = Logger.getLogger("com.logger.jul.JULTest");

        // 输出日志
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info"); // jul 默认日志级别是 info 比info低的不会输出
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");

    }

    /**
     * 自定义日志级别
     * @throws Exception
     */
    @Test
    public void testConfigLevel() throws Exception{
        Logger logger = Logger.getLogger("com.logger.jul.JULTest");

        // 先关闭默认的配置
        logger.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        SimpleFormatter simpleFormatter = new SimpleFormatter();

        // 建立联系
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL); // all 表示所有日志级别都会生效
        consoleHandler.setLevel(Level.ALL);

        // 文件日志输出 此路径是项目所在磁盘 logs/jul.log
        FileHandler fileHandler = new FileHandler("/logs/jul.log");

        //  建立联系
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);
        fileHandler.setLevel(Level.ALL);

        // 输出日志
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info"); // jul 默认日志级别是 info 比info低的不会输出
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");

    }


    /**
     * 父子关系
     * @throws Exception
     */
    @Test
    public void testParent() throws Exception{
        Logger logger1 = Logger.getLogger("com.logger");
        Logger logger2 = Logger.getLogger("com");

        // 是相等的
        System.out.println(logger1.getParent() == logger2);
        // java.util.logging.LogManager$RootLogger 是所有的顶级父类
        System.out.println(logger2.getParent() + "名字为:" + logger2.getParent().getName());

        // 先关闭默认的配置
        logger2.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        SimpleFormatter simpleFormatter = new SimpleFormatter();

        // 建立联系
        consoleHandler.setFormatter(simpleFormatter);
        logger2.addHandler(consoleHandler);
        logger2.setLevel(Level.ALL); // all 表示所有日志级别都会生效
        consoleHandler.setLevel(Level.ALL);


        // logger2自定义全级别,但是用logger1来打印 发现是可以全部打印的,印证了父子关系
        logger1.severe("severe");
        logger1.warning("warning");
        logger1.info("info"); // jul 默认日志级别是 info 比info低的不会输出
        logger1.config("config");
        logger1.fine("fine");
        logger1.finer("finer");
        logger1.finest("finest");

    }

    /**
     * 自定义配置文件
     */
    @Test
    public void testLoggingProperties() throws Exception{

        // 读取配置文件
        InputStream is = JULTest.class.getClassLoader().getResourceAsStream("logging.properties");
        LogManager logManager = LogManager.getLogManager();
        logManager.readConfiguration(is);

        Logger logger = Logger.getLogger("com.logger.jul.JULTest");
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info"); // jul 默认日志级别是 info 比info低的不会输出
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }
}
