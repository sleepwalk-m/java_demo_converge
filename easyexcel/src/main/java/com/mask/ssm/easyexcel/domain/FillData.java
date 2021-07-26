package com.mask.ssm.easyexcel.domain;

import lombok.Data;

/**
 * 使用实体类封装填充数据
 *
 *  实体中成员变量名称需要和Excel表各种{}包裹的变量名匹配
 */
@Data
public class FillData {

    private String name;
    private int age;
}