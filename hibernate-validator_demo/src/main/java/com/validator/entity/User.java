package com.validator.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @author: Mask.m
 * @create: 2021/10/02 16:08
 * @description:
 */
@Data
public class User {

    @NotNull(message = "用户id不能为空")
    private Integer id;

    @NotEmpty(message = "用户名不能带空格")
    @Length(max = 50,message = "用户名长度不能超过50")
    private String username;

    @Max(value = 80,message = "最大年龄不能超过80")
    @Min(value = 18,message = "最小年龄不能小于18")
    private Integer age;

    @Email(message = "邮箱格式不正确")
    private String email;

}
