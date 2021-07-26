package com.mask.ssm.service;

import com.mask.ssm.domain.JobInfo;

/**
 * @author: Mask.m
 * @create: 2021/07/26 20:59
 * @description:
 */
public interface JobInfoService {

    JobInfo findById(Integer id);
}
