package com.knife.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Mask.m
 * @create: 2021/09/12 14:52
 * @description: 用户实体
 */
@Data
@ApiModel(value = "用户实体",description = "用户实体")
public class User {

    @ApiModelProperty(value = "主键")
    private int id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "年龄")
    private int age;
    @ApiModelProperty(value = "地址")
    private String address;
}
