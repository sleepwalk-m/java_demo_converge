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


    // 1. 去请求京东首页
    // 2. 搜索手机，拿到浏览器渲染后的页面    --》 puppeteer 来做
    // 3. 解析这个渲染后的html 拿到所有的列表中的详情页url
    // 4. 去请求详情页的url
    // 5. 解析详情页 保存数据


    /**
     * 1. 动态爬虫应用场景
     *         1. 反爬 加密网站
     *  2. puppeteer 跟 selenium的区别
     *      1。 puppeteer是node服务版，可以多线程  puppeteer更强大 扩展更强 需要开启服务器
     *      2. selenim 要写在Java中 只能单线程
     *
     */


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
