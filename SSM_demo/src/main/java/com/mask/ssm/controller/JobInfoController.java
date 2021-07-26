package com.mask.ssm.controller;

import com.mask.ssm.domain.JobInfo;
import com.mask.ssm.service.JobInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mask.m
 * @create: 2021/07/26 21:03
 * @description:
 */
@RestController
public class JobInfoController {

    @Autowired
    private JobInfoService jobInfoService;

    @GetMapping("/job/{id}")
    public JobInfo findById(@PathVariable("id") Integer id){
        return jobInfoService.findById(id);
    }
}
