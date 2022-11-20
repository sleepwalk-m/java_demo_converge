package com.mask.cultural;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: Mask.m
 * @create: 2022/04/10 13:00
 * @description:
 */
public class CulturalCrawlerPageProcessor implements PageProcessor {


    @Override
    public void process(Page page) {
        Map<String, Object> extras = page.getRequest().getExtras();
        String level = extras.get("pageLevel").toString();

        String rawText = page.getRawText();
        System.out.println("result = " + rawText);


    }



    @Override
    public Site getSite() {
        HashSet<Integer> set = new HashSet<>();
        set.add(403);
        set.add(500);
        set.add(200);

        return Site.me().setTimeOut(30000).setAcceptStatCode(set).setCharset("utf-8");
    }

}
