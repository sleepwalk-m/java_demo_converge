package com.gov.encrypt.compoent;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2022/03/27 15:07
 * @description: 爬虫的启动
 */
public class GovCrawlerSpider {
    public static void main(String[] args) {

        /*
         1. 请求验证码图片
         2. 校验验证码
         3. 数据请求

         // 作业：https://jdcjcwx.jtzyzg.org.cn/JDCJCWX/LEAP/jdcjcwx/html/publicQuery.html
            可以尝试看下这个站点，需要如何处理加密
          */
        String url = "http://app.gjzwfw.gov.cn/jmopen/verifyCode.do?width=100&height=55&random=" + Math.random();
        Request request = new Request(url);

        Map<String,Object> extra = new HashMap<>();
        extra.put("pageLevel","image");
        request.setExtras(extra);

        Spider.create(new GovCrawlerPageProcessor())
                .addRequest(request)
                .thread(1)
                .start();
    }
}
