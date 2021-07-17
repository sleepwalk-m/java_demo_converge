package com.mask.task.compoent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * @author: Mask.m
 * @create: 2021/07/17 10:38
 * @description: 定时启动爬虫任务
 */
@Component
public class JdSpider {

    @Value("${indexUrl}")
    private String indexUrl;

    @Autowired
    private JdPageProcessor pageProcessor;

    @Autowired
    private JdPipeline pipeline;

    @Autowired
    private JdDownloader downloader;


    @Scheduled(fixedRate = 3600*24)
    public void doCrawler(){
        // 添加代理
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("117.70.40.50",33542)));

        // 设置布隆过滤器去重策略
        QueueScheduler scheduler = new QueueScheduler();
        scheduler.setDuplicateRemover(new BloomFilterDuplicateRemover(1000000));

        Request request = new Request(indexUrl);
        request.putExtra("level","list");
        request.putExtra("pageNum","1");

        Spider.create(pageProcessor)
                .addPipeline(pipeline)
                .setDownloader(httpClientDownloader)
                .setDownloader(downloader)
                .setScheduler(scheduler)
                .addRequest(request)
                .thread(1)
                .start();
    }
}
