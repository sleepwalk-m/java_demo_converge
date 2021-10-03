package com.xss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.xss.filter")
public class AntiSamyApp {
    public static void main(String[] args) {
        SpringApplication.run(AntiSamyApp.class,args);
    }
}