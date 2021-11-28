package com.quartz.boot.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: Mask.m
 * @create: 2021/11/28 13:10
 * @description: 自定义job
 */
public class HelloJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        String time = formatter.format(now);

        Object bizId = jobExecutionContext.getJobDetail().getJobDataMap().get("bizId");
        System.out.println("自定义Job...bizId = " + bizId + " 执行时间" + time);
    }
}
