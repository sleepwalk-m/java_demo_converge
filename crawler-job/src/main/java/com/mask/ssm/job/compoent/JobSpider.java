package com.mask.ssm.job.compoent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2021/07/14 20:56
 * @description: 爬虫启动
 */
@Component
public class JobSpider {

    private static final String URL = "https://search.51job.com/list/080200,000000,0000,00,9,99,java,2,2.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";

    @Autowired
    private JobPageProcessor jobPageProcessor;

    @Autowired
    private JobPipeline jobPipeline;

    @Autowired
    private Scheduler scheduler;

    public void doCrawler(){

        Request request = new Request(URL);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("level","list"); // 标识位
        request.setExtras(map);
        request.addHeader("Accept","application/json, text/javascript, */*; q=0.01");

        Spider.create(jobPageProcessor)
                .addPipeline(jobPipeline)
                .setScheduler(scheduler)
                .addRequest(request)
                .start();
    }
}
