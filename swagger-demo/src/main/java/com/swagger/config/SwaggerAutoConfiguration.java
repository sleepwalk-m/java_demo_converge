package com.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: Mask.m
 * @create: 2021/09/12 15:17
 * @description: 配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {

    @Bean
    public Docket getRestApi1(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 设置一些基础信息
                .groupName("用户接口组") // 设置组名，如果该配置类只有一个方法返回docket对象，则是默认组，所有的接口在一个文档
                .select()// 指定扫描包必须先写这个
                .apis(RequestHandlerSelectors.basePackage("com.swagger.controller"))// 设置包扫描路径 扫描这个下面的接口
                .build()
                ;
        return docket;
    }


    // 将该方法注掉，只剩一个方法，则是默认用户组
    /*@Bean
    public Docket getRestApi2(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 设置一些基础信息
                .groupName("菜单接口组") // 设置组名，如果该配置类只有一个方法返回docket对象，则是默认组，所有的接口在一个文档
                .select()// 指定扫描包必须先写这个
                .apis(RequestHandlerSelectors.basePackage("com.swagger.controller.menu"))// 设置包扫描路径 扫描这个下面的接口
                .build()
                ;
        return docket;
    }*/




    private ApiInfo apiInfo(){
       return new ApiInfoBuilder()
                .title("我的API接口文档")
                .contact(new Contact("Mask","aaa","xxx@xx.cn"))
                .description("我的接口文档")
                .version("1.0")
                .build();
    }
}
