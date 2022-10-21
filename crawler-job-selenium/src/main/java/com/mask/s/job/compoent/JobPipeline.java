package com.mask.s.job.compoent;

import com.mask.s.job.mapper.JobInfoMapper;
import com.mask.s.job.pojo.JobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author: Mask.m
 * @create: 2021/07/14 20:53
 * @description:
 */
public class JobPipeline implements Pipeline {


    private JobInfoMapper jobInfoMapper;

    /**
     *
     *
     * @param resultItems 返回的结果
     * @param task
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        // 取出数据
        JobInfo jobInfo = resultItems.get("jobInfo");
        System.out.println("jobInfo = " + jobInfo);
        // 保存到数据库
        if (jobInfo != null){
            //jobInfoMapper.insert(jobInfo);
        }
    }
}
