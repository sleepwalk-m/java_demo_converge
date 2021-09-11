package com.mask.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Mask.m
 * @create: 2021/09/11 18:49
 * @description: 配置文件，读取yml配置中的数据
 */
@Data
@ConfigurationProperties(prefix = "hello")
public class HelloProperties {

    private String name;
    private String address;
}
