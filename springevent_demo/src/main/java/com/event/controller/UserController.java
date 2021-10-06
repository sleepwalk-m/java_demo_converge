package com.event.controller;

import com.event.dto.OptLogDTO;
import com.event.event.SysLogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mask.m
 * @create: 2021/10/06 20:13
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ApplicationContext applicationContext;
    @GetMapping("/getUser")
    public String getUser(){
        //构造操作日志信息
        OptLogDTO logInfo = new OptLogDTO();
        logInfo.setRequestIp("127.0.0.1");
        logInfo.setUserName("admin");
        logInfo.setType("OPT");
        logInfo.setDescription("查询用户信息");

        //构造事件对象
        ApplicationEvent event = new SysLogEvent(logInfo);

        //发布事件
        applicationContext.publishEvent(event);

        long id = Thread.currentThread().getId();
        System.out.println("发布事件,线程id：" + id);
        return "OK";
    }
}
