package com.event.listener;

import com.event.dto.OptLogDTO;
import com.event.event.SysLogEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author: Mask.m
 * @create: 2021/10/06 20:11
 * @description: 创建事件监听
 */
@Component
public class SysLogListener {

    //@Async 异步操作
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event){
        OptLogDTO dto = (OptLogDTO) event.getSource();
        long id = Thread.currentThread().getId();
        System.out.println("监听到日志操作事件：" + dto + " 线程id：" + id);
        //将日志信息保存到数据库...
    }

}
