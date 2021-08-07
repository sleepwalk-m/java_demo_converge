package com.mask.xxljob.service;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: Mask.m
 * @create: 2021/08/07 15:06
 * @description: 测试实例
 */
@Component // 注意一定要加 给spring管理
public class SampleXxlJob {

    @Value("${server.port}")
    private Integer port;

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("HelloJob222")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");


        System.out.println("任务执行了。。。" + new Date() + "端口为：" + port );

        return ReturnT.SUCCESS;
    }
}
