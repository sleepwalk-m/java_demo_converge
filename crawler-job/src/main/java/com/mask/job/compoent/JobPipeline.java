package com.mask.job.compoent;

import com.mask.job.mapper.JobInfoMapper;
import com.mask.job.pojo.JobInfo;
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
@Component
public class JobPipeline implements Pipeline {

    @Autowired
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

        // 保存到数据库
        jobInfoMapper.insert(jobInfo);
    }
}
