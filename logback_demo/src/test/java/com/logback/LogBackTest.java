package com.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Mask.m
 * @create: 2021/10/06 18:49
 * @description:
 */
public class LogBackTest {

    /**
     * 简单使用logback
     */
    @Test
    public void test1(){
        // 获取logger日志记录器对象
        Logger logger = LoggerFactory.getLogger("mask");
        // 当前logger日志输出级别是debug 从root logger继承的

        // trace级别记录日志
        logger.trace("trace...");
        logger.debug("debug...");
        logger.info("info...");
        logger.warn("warn...");
        logger.error("error...");

    }

    /**
     * 打印日志内部状态
     */
    @Test
    public void test2(){

        // 获取logger日志记录器对象
        Logger logger = LoggerFactory.getLogger("mask");
        // 当前logger日志输出级别是debug 从root logger继承的

        // trace级别记录日志
        logger.trace("trace...");
        logger.debug("debug...");
        logger.info("info...");
        logger.warn("warn...");
        logger.error("error...");

        // 打印内部的状态
        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);


    }

    /**
     * 日志输出级别
     */
    @Test
    public void test3(){

        // 获取logger日志记录器对象
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("mask");
        // 设置日志输出等级 注意这里的logger是实现类
        logger.setLevel(Level.ERROR);

        // trace级别记录日志
        logger.trace("trace...");
        logger.debug("debug...");
        logger.info("info...");
        logger.warn("warn...");
        logger.error("error...");

    }


    /**
     * 日志级别的继承
     */
    @Test
    public void test4(){

        // 获取logger日志记录器对象
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("com.mask");
        // 设置日志输出等级 注意这里的logger是实现类
        logger.setLevel(Level.INFO);

        // trace级别记录日志
        logger.trace("trace...");
        logger.debug("debug...");
        logger.info("info...");
        logger.warn("warn...");
        logger.error("error...");

        // 当前记录器对象是上面的子级，继承了级别
        Logger logger1 = LoggerFactory.getLogger("com.mask.logback");
        // 上面是info级别 debug < info 这句不会输出
        logger1.debug("调试信息");
        // info >= info 这句会输出
        logger1.info("日志信息");

    }





    //Logger获取，根据同一个名称获得的logger都是同一个实例
    @Test
    public void test6(){
        Logger logger1 = LoggerFactory.getLogger("cn.mask");
        Logger logger2 = LoggerFactory.getLogger("cn.mask");
        System.out.println(logger1 == logger2);
    }

    //参数化日志
    @Test
    public void test7(){
        Logger logger = LoggerFactory.getLogger("cn.mask");
        logger.debug("hello {}","world");
    }
}
