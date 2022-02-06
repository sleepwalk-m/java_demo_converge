package com.logger.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.junit.Test;

/**
 * @author: Mask.m
 * @create: 2022/02/05 21:45
 * @description: log4j的测试类
 */
public class Log4jTest {


    @Test
    public void testQuick() throws Exception{

        // 开启log4j内置的日志信息 记录log4j操作的一些日志 例如读取配置文件
        LogLog.setInternalDebugging(true);
        // 初始化系统配置，当前快速入门使用编程式完成，不使用配置文件
        //BasicConfigurator.configure();
        // 获取logger对象
        Logger logger = Logger.getLogger(Log4jTest.class);

        logger.fatal("fatal");// 致命错误，会导致系统退出
        logger.error("error ");// 错误 不会影响系统运行
        logger.warn("warn ");// 警告信息 可能会发生异常
        logger.info("hello log4j");// 提示信息 例如数据库连接 IO操作等等
        logger.debug("debug"); // 开发阶段使用 看方法入参等
        logger.trace("trace"); // 追踪信息，记录程序的所有流程信息

    }

    /**
     * 测试日志输出到数据库
     * @throws Exception
     */
    @Test
    public void testProperties() throws Exception{

        // 开启log4j内置的日志信息 记录log4j操作的一些日志 例如读取配置文件
        LogLog.setInternalDebugging(true);
        // 初始化系统配置，当前快速入门使用编程式完成，不使用配置文件
        //BasicConfigurator.configure();
        // 获取logger对象
        Logger logger = Logger.getLogger(Log4jTest.class);



        for (int i = 0; i < 100; i++) {

            logger.fatal("fatal");// 致命错误，会导致系统退出
            logger.error("error ");// 错误 不会影响系统运行
            logger.warn("warn ");// 警告信息 可能会发生异常
            logger.info("hello log4j");// 提示信息 例如数据库连接 IO操作等等
            logger.debug("debug"); // 开发阶段使用 看方法入参等
            logger.trace("trace"); // 追踪信息，记录程序的所有流程信息
        }

    }

    /**
     * 自定义logger
     * @throws Exception
     */
    @Test
    public void testCustomLogger() throws Exception {
    // 自定义 com.mask
        Logger logger1 = Logger.getLogger(Log4jTest.class);
        logger1.fatal("fatal"); // 严重错误，一般会造成系统崩溃和终止运行
        logger1.error("error"); // 错误信息，但不会影响系统运行
        logger1.warn("warn"); // 警告信息，可能会发生问题
        logger1.info("info"); // 程序运行信息，数据库的连接、网络、IO操作等
        logger1.debug("debug"); // 调试信息，一般在开发阶段使用，记录程序的变量、参数等
        logger1.trace("trace"); // 追踪信息，记录程序的所有流程信息
    // 自定义 org.apache
        Logger logger2 = Logger.getLogger(Logger.class);
        logger2.fatal("fatal logger2"); // 严重错误，一般会造成系统崩溃和终止运行
        logger2.error("error logger2"); // 错误信息，但不会影响系统运行
        logger2.warn("warn logger2"); // 警告信息，可能会发生问题
        logger2.info("info logger2"); // 程序运行信息，数据库的连接、网络、IO操作等
        logger2.debug("debug logger2"); // 调试信息，一般在开发阶段使用，记录程序的变量、参数等
        logger2.trace("trace logger2"); // 追踪信息，记录程序的所有流程信息
    }
}
