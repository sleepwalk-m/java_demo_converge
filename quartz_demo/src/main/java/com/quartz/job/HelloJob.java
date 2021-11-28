package com.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author: Mask.m
 * @create: 2021/11/28 11:40
 * @description: quartz的任务 实现job接口 在execute方法执行自己的逻辑
 */
public class HelloJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("现在的时间为：" + LocalDateTime.now());
    }
}
