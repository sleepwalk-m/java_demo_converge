package com.logger.slf4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Mask.m
 * @create: 2022/02/06 14:22
 * @description:
 */
public class Slf4jTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(Slf4jTest.class);

    @Test
    public void testQuick() throws Exception{

        LOGGER.info(LOGGER.getClass().getName()); // org.slf4j.impl.SimpleLogger
        LOGGER.error("error ");
        LOGGER.warn("warning ");
        LOGGER.info("info ");
        LOGGER.debug("debug ");
        LOGGER.trace("trace ");


        // 占位符打印
        String name = "mask";
        Integer age = 10;
        LOGGER.info("参数信息：{},{}",name,age);


        // 错误日志
        try {
            int i = 1/0;
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("错误信息：" ,e);
        }


    }
}
