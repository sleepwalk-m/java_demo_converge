package com.quartz.boot.controller;

import com.quartz.boot.jobs.HelloJob;
import io.swagger.annotations.ApiOperation;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quartz")
public class QuartzController {
    //注入调度器对象
    @Autowired
    private Scheduler scheduler;


    @PostMapping("")
    @ApiOperation("添加定时任务")
    public String save(String bizId,String cronExpression) throws SchedulerException {

        // 构建job信息
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity(bizId).build();
        jobDetail.getJobDataMap().put("bizId",bizId);


        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();

        // 构建触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(bizId).withSchedule(cronScheduleBuilder).build();

        // 关联任务和触发器
        scheduler.scheduleJob(jobDetail,trigger);

        return "ok";
    }

    @PutMapping("/pause/{bizId}")
    @ApiOperation("暂停定时任务")
    public String pause(@PathVariable String bizId) throws SchedulerException {

        // 暂停
        scheduler.pauseJob(JobKey.jobKey(bizId));

        return "ok";
    }

    @PutMapping("/resume/{bizId}")
    @ApiOperation("恢复定时任务")
    public String resume(@PathVariable String bizId) throws SchedulerException {

        // 恢复
        scheduler.resumeJob(JobKey.jobKey(bizId));

        return "ok";
    }


    @DeleteMapping
    @ApiOperation("删除定时任务")
    public String delete(String bizId) throws SchedulerException {

        // 删除
        scheduler.deleteJob(JobKey.jobKey(bizId));

        return "ok";
    }

    @PutMapping("/run/{bizId}")
    @ApiOperation("立即执行定时任务")
    public String run(@PathVariable String bizId) throws SchedulerException {

        // 立即执行  请求一次执行一次
        scheduler.triggerJob(JobKey.jobKey(bizId));

        return "ok";
    }


    @PutMapping
    @ApiOperation("更新定时任务")
    public String update(String bizId,String cronExpression) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(bizId);
        // 获取原来的trigger对象
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);


        // 放入新的表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();

        // 重新构造新的trigger
        trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

        // 关联任务和触发器
        scheduler.rescheduleJob(triggerKey,trigger);

        return "ok";
    }

}