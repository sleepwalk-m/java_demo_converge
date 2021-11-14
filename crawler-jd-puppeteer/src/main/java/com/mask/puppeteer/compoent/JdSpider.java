package com.mask.puppeteer.compoent;

import com.mask.puppeteer.util.PuppeteerUtil;
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


    //@Scheduled(fixedRate = 3600*72)
    public void crawler(){
        // 添加代理
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("117.70.40.50",33542)));

        Request request = new Request(indexUrl);
        request.putExtra("level","list");
        request.putExtra("pageNum","1");

        Spider.create(pageProcessor)
                .addPipeline(pipeline)
                .setDownloader(httpClientDownloader)
                .setDownloader(downloader)
                .addRequest(request)
                .thread(1)
                .start();
    }
}
