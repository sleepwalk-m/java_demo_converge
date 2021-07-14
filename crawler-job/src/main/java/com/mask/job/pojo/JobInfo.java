package com.mask.job.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("job_info")
public class JobInfo implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField("company_name")
    private String companyName;
    @TableField("company_addr")
    private String companyAddr;
    @TableField("company_info")
    private String companyInfo;
    @TableField("job_name")
    private String jobName;
    @TableField("job_addr")
    private String jobAddr;
    @TableField("job_info")
    private String jobInfo;
    @TableField("salary_min")
    private Float salaryMin;
    @TableField("salary_max")
    private Float salaryMax;
    @TableField("url")
    private String url;
    @TableField("time")
    private String time;
}