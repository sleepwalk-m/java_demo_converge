package com.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Mask.m
 * @create: 2021/09/12 14:53
 * @description: 菜单实体
 */
@Data
@ApiModel(value = "菜单实体",description = "菜单实体")
public class Menu {

    @ApiModelProperty(value = "主键")
    private int id;
    @ApiModelProperty(value = "菜单名称")
    private String name;
}
