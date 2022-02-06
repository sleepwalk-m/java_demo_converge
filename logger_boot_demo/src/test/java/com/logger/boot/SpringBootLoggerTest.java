package com.logger.boot;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Mask.m
 * @create: 2022/02/06 18:08
 * @description:
 */
@SpringBootTest
public class SpringBootLoggerTest {

    private  final Logger LOGGER = LoggerFactory.getLogger(SpringBootLoggerTest.class);

    /**
     * slf4j门面 logback实现
     */
    @Test
    public void test(){

        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");

        LOGGER.info("当前logger对象：" + LOGGER.getClass().getName());

    }

    /**
     * slf4j门面 log4j2实现
     */
    @Test
    public void testLog4j2(){

        org.apache.logging.log4j.Logger logger = LogManager.getLogger(SpringBootLoggerTest.class);

        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("trace");

        logger.info("当前logger对象：" + logger.getClass().getName());

    }
}
