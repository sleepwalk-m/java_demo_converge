package com.mask.ssm.service.impl;

import com.mask.ssm.dao.JobInfoMapper;
import com.mask.ssm.domain.JobInfo;
import com.mask.ssm.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Mask.m
 * @create: 2021/07/26 21:01
 * @description:
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    JobInfoMapper jobInfoMapper;

    public JobInfo findById(Integer id) {
        return jobInfoMapper.findById(id);
    }
}
