package com.mask.config;

import com.mask.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Mask.m
 * @create: 2021/09/11 18:52
 * @description: 自动配置类
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

    private HelloProperties helloProperties;
    // 使用构造器注入 helloproperties
    public HelloServiceAutoConfiguration(HelloProperties helloProperties){
        this.helloProperties = helloProperties;
    }


    // 实例化对象并放入spring容器，通知指定当容器中没有这个bean的时候才去创建
    @ConditionalOnMissingBean(HelloService.class)
    @Bean
    public HelloService helloService(){
        return new HelloService(this.helloProperties.getName(),this.helloProperties.getAddress());
    }


}
