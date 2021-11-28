package com.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * @author: Mask.m
 * @create: 2021/11/28 11:40
 * @description: quartz的任务 实现job接口 在execute方法执行自己的逻辑
 */
public class HelloJob implements Job {

    public HelloJob(){
        System.out.println("hello job 被创建了");
    }

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("现在的时间为：" + LocalDateTime.now());

        // 从上下文对象中取出 参数
        Set<Map.Entry<String, Object>> set = jobExecutionContext.getJobDetail().getJobDataMap().entrySet();
        set.stream().forEach(entry -> {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key + ":" +  value);
        });

    }
}
