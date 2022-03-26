package com.book;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2022/03/18 23:49
 * @description: 解析页面
 */
public class MyBookPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(30000);

    /**
     * 解析页面的逻辑
     *
     * @param page
     */
    @Override
    public void process(Page page) {

        // 首页 -》 列表 -》 详情
        Map<String, Object> extraMap = page.getRequest().getExtras();
        String level = extraMap.get("level").toString();

        switch (level) {
            case "index":
                parseIndex(page);
                break;
            case "list":
                parseList(page);
                break;
            case "detail":
                parseDetail(page);
                break;
        }


    }

    /**
     * 解析详情 小说正文
     * @param page
     */
    private void parseDetail(Page page) {
        Html html = page.getHtml();

        Map<String, Object> extras = page.getRequest().getExtras();
        String title = extras.get("title").toString();


        String articleTitle = html.xpath("[@class=ydleft]/h1/text()").get();
        String content = html.xpath("[@class=yd_text2]/allText()").get();


        page.putField("title",title);
        page.putField("articleTitle",articleTitle);
        page.putField("content",content);
        page.putField("level","detail");

    }

    /**
     * 解析列表页
     *
     * @param page
     */
    private void parseList(Page page) {
        Html html = page.getHtml();

        List<Selectable> aList = html.xpath("[@class=abab]").nodes();
        for (Selectable a : aList) {
            String detailUrl = "https://www.ff96.net/" + a.xpath("///@href").get();

            Request request = new Request(detailUrl);
            Map<String, Object> extraMap = new HashMap<>();
            extraMap.putAll(page.getRequest().getExtras());
            extraMap.put("level", "detail");
            request.setExtras(extraMap);
            page.addTargetRequest(request);
        }


    }

    /**
     * 解析首页
     *
     * @param page
     */
    private void parseIndex(Page page) {

        Html html = page.getHtml();
        //List<Selectable> nodes = html.$(".tab_main > ul:nth-child(1) > div").nodes();

        List<Selectable> divList = html.xpath("[@class=tab_main]/ul[1]/div").nodes();

        for (Selectable div : divList) {
            String bookUrl = div.xpath("//p[1]/a/@href").get();
            String title = div.xpath("//p[1]/a/@title").get();

            Request request = new Request(bookUrl);
            Map<String, Object> extraMap = new HashMap<>();
            extraMap.put("level", "list");
            extraMap.put("title", title);// 书名

            request.setExtras(extraMap);

            page.addTargetRequest(request);
        }

    }


    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {

        String url = "https://www.ff96.net/";
        Request request = new Request(url);
        Map<String, Object> extraMap = new HashMap<>();

        extraMap.put("level", "index");// index -> 首页
        request.setExtras(extraMap);


        Spider.create(new MyBookPageProcessor())
                .addRequest(request)
                .addPipeline(new MyBookPipeline())
                .thread(30)
                .start();

    }
}
