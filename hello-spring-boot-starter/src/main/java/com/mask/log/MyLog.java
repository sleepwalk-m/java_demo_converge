package com.mask.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: Mask.m
 * @create: 2021/09/11 20:11
 * @description: 自定义日志的注解，标识使用这个注解的需要进行日志记录
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog {
    /**
     * 方法描述
     */
    String desc() default "";
}
