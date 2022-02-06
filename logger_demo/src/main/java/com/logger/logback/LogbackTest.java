package com.logger.logback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Mask.m
 * @create: 2022/02/06 15:22
 * @description:
 */
public class LogbackTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackTest.class);

    @Test
    public void testQuick() throws Exception{

        //打印日志信息
        for (int i = 0;i <= 10000;i++) {
            LOGGER.error("error");
            LOGGER.warn("warn");
            LOGGER.info("info");
            LOGGER.debug("debug");
            LOGGER.trace("trace");

            LOGGER.info("当前logger对象：" + LOGGER.getClass().getName());
        }
    }
}
