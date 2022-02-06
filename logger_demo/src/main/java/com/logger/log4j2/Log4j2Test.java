package com.logger.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.slf4j.LoggerFactory;

/**
 * @author: Mask.m
 * @create: 2022/02/06 16:40
 * @description:
 */
public class Log4j2Test {

    private static final Logger LOGGER = LogManager.getLogger(Log4j2Test.class);

    /**
     * log4j2 作为日志门面+实现
     */
    @Test
    public void testQuick(){

        LOGGER.fatal("fatal");// 致命错误，会导致系统退出
        LOGGER.error("error ");// 错误 不会影响系统运行
        LOGGER.warn("warn ");// 警告信息 可能会发生异常
        LOGGER.info("hello log4j");// 提示信息 例如数据库连接 IO操作等等
        LOGGER.debug("debug"); // 开发阶段使用 看方法入参等
        LOGGER.trace("trace"); // 追踪信息，记录程序的所有流程信息
    }

    /**
     * slf4j 作为日志门面 log4j2作为实现
     */
    @Test
    public void testSlf(){
        LOGGER.fatal("fatal");// 致命错误，会导致系统退出
        LOGGER.error("error ");// 错误 不会影响系统运行
        LOGGER.warn("warn ");// 警告信息 可能会发生异常
        LOGGER.info("hello log4j");// 提示信息 例如数据库连接 IO操作等等
        LOGGER.debug("debug"); // 开发阶段使用 看方法入参等
        LOGGER.trace("trace"); // 追踪信息，记录程序的所有流程信息
    }
}
