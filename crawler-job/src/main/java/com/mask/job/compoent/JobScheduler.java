package com.mask.job.compoent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

/**
 * @author: Mask.m
 * @create: 2021/07/14 20:50
 * @description:
 */
@Configuration
public class JobScheduler {

    @Bean
    public Scheduler scheduler(){
        BloomFilterDuplicateRemover bm = new BloomFilterDuplicateRemover(10000000);
        return new QueueScheduler().setDuplicateRemover(bm);
    }
}
