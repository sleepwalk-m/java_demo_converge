package com.mask.ssm.dao;

import com.mask.ssm.domain.JobInfo;
import org.apache.ibatis.annotations.Select;

/**
 * @author: Mask.m
 * @create: 2021/07/26 20:54
 * @description: dao接口
 */
public interface JobInfoMapper {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from job_info where id = #{id}")
    JobInfo findById(Integer id);
}
