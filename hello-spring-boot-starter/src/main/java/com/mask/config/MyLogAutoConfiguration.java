package com.mask.config;

import com.mask.log.MyLogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Mask.m
 * @create: 2021/09/11 20:28
 * @description: 拦截器的配置 用于自动配置拦截器、参数解析器等web组件
 */
@Configuration
public class MyLogAutoConfiguration implements WebMvcConfigurer {

    //注册自定义日志拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyLogInterceptor());
    }
}
