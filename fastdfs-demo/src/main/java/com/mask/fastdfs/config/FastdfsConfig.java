package com.mask.fastdfs.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: Mask.m
 * @create: 2021/06/27 15:45
 * @description: fastdfs的配置类
 */
@Configuration
@PropertySource("classpath:fast_dfs.properties")
@Import(FdfsClientConfig.class)
public class FastdfsConfig {
}
