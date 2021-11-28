package com.quartz.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

/**
 * @author: Mask.m
 * @create: 2021/11/28 11:42
 * @description: 定时任务的执行 启动调度任务
 */
public class MainJob {

    public static void main(String[] args) throws Exception{
        // 1.使用任务调度器工厂 创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 2.使用r任务构建器 创建任务对象
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myjobdetail").build();

        // 保存了一些状态信息，由此传参到自定义的job
        jobDetail.getJobDataMap().put("k1","v1");
        jobDetail.getJobDataMap().put("k2","v2");



        // 3.使用触发器构建器 创建c触发器对象

        // 使用了简单的schedule 设置3秒一次调度 并且永不停止
        //SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("mytrigger").withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();
        // 使用cron表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("1,5,11,20,27,35 * * * * ?");

        // 用cron触发的写法
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("mytrigger")
                .withSchedule(cronScheduleBuilder).build();


        // 4.触发器和任务关联
        scheduler.scheduleJob(jobDetail,trigger);

        // 5.启动调度
        scheduler.start();
    }
}
