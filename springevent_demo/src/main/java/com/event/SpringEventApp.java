package com.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync//启用异步处理
public class SpringEventApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringEventApp.class,args);
    }
}