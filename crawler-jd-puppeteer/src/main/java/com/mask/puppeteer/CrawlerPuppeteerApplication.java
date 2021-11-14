package com.mask.puppeteer;

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
//@EnableScheduling
public class CrawlerPuppeteerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrawlerPuppeteerApplication.class,args);
    }
}
