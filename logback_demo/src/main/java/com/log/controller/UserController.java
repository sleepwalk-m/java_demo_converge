package com.log.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Log4j2 // 默认是根据当前环境的配置来的 用的是pro环境
public class UserController {
    //Logger logger = LoggerFactory.getLogger("");

    @GetMapping("/get")
    public String get(){
        log.trace("trace...");
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");
        return "OK";
    }
}