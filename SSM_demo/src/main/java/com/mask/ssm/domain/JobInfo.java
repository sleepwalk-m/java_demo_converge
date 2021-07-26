package com.mask.ssm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Mask.m
 * @create: 2021/07/26 20:53
 * @description: 51job 职位的实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobInfo {
    private Long id;
    private String companyName;
    private String companyAddr;
    private String companyInfo;
    private String jobName;
    private String jobAddr;
    private String jobInfo;
    private Float salaryMin;
    private Float salaryMax;
    private String url;
    private String time;
}
