package com.logger.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @author: Mask.m
 * @create: 2022/02/06 13:53
 * @description:
 */
public class JCLTest {


    @Test
    public void testQuick() throws Exception{

        // 接口的形式 根据导入的日志不同 获取对应的logger对象 这里的就是log4jlogger
        Log log = LogFactory.getLog(JCLTest.class);

        log.info(log.getClass()); // org.apache.commons.logging.impl.Log4JLogger

    }
}
