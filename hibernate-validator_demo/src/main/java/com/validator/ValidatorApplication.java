package com.validator;

import com.validator.config.EnableFormValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Mask.m
 * @create: 2021/10/02 16:18
 * @description:
 */
@SpringBootApplication
@EnableFormValidator // 自定义注解 启用快速失败的模式（校验第一个不通过就直接返回 不继续往后校验）
public class ValidatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValidatorApplication.class,args);
    }
}
