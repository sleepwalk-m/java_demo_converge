package com.event.event;

import com.event.dto.OptLogDTO;
import org.springframework.context.ApplicationEvent;

/**
 * @author: Mask.m
 * @create: 2021/10/06 20:10
 * @description: 定义系统日志事件
 */
public class SysLogEvent extends ApplicationEvent{

    public SysLogEvent(OptLogDTO dto) {
        super(dto);
    }
}
