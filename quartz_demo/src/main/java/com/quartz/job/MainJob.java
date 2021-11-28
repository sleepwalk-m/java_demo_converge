package com.quartz.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author: Mask.m
 * @create: 2021/11/28 11:42
 * @description: 定时任务的执行 启动调度任务
 */
public class MainJob {

    public static void main(String[] args) throws Exception{
        // 使用任务调度器工厂 创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 使用r任务构建器 创建任务对象
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myjobdetail").build();

        // 使用触发器构建器 创建c触发器对象
        // 是使用了简单的schedule 设置3秒一次调度 并且永不停止
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("mytrigger").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();

        // 触发器和任务关联
        scheduler.scheduleJob(jobDetail,trigger);

        // 启动调度
        scheduler.start();
    }
}
