package com.validator.controller;

import com.alibaba.fastjson.JSON;
import com.validator.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author: Mask.m
 * @create: 2021/10/02 16:15
 * @description:
 */
@RestController
@RequestMapping("/user")
@Validated
public class ValidatorController {

    // 简单数据类型的校验
    @GetMapping("/del")
    public String delete(@NotNull(message = "用户id不能为空") Integer id){
        System.out.println("delete。。。" + id);
        return "delete success";
    }


    @GetMapping("/save")
    public String getUser(@Validated User user){
        System.out.println("save...");
        return "Save success";
    }


}
