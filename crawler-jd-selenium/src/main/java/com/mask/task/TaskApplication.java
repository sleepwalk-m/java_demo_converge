package com.mask.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: Mask.m
 * @create: 2021/07/17 08:59
 * @description:
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class,args);
    }
}
