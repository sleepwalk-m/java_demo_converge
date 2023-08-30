package com.mask.js.job.compoent;

import com.mask.js.job.utils.HeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

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
    private static final String URL1 = "https://we.51job.com/api/job/search-pc?api_key=51job&timestamp=1691160473&keyword=java&searchType=2&function=&industry=&jobArea=000000&jobArea2=&landmark=&metro=&salary=&workYear=&degree=&companyType=&companySize=&jobType=&issueDate=&sortType=0&pageNum=1&requestId=2a554e056482df976d49dc07aeb0c551&pageSize=20&source=1&accountId=&pageCode=sou%7Csou%7Csoulb";

    @Autowired
    private JobPageProcessor jobPageProcessor;

    @Autowired
    private JobPipeline jobPipeline;

    @Autowired
    private Scheduler scheduler;

    public void doCrawler(){

        Downloader downloader = new JobDownloader();
        Request request = new Request(URL1);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("level","index"); // 标识位
        request.setExtras(map);
        request.addHeader("Accept","application/json, text/javascript, */*; q=0.01");
        HeaderUtils.setGetHeaders(request);
        Spider.create(jobPageProcessor)
//                .addPipeline(jobPipeline)
//                .setScheduler(scheduler)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new JobDuplicateRemover()))
                .addRequest(request)
                .setDownloader(downloader)
                .thread(1)
                .start();
    }
}
