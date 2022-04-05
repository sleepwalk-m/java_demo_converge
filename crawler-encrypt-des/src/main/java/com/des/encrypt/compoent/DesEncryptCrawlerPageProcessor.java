package com.des.encrypt.compoent;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2022/03/27 12:38
 * @description:
 */
public class DesEncryptCrawlerPageProcessor implements PageProcessor {


    @Override
    public void process(Page page) {
        Map<String, Object> extras = page.getRequest().getExtras();
        String level = extras.get("pageLevel").toString();

        System.out.println(page.getRawText());

    }



    @Override
    public Site getSite() {
        return Site.me().setTimeOut(30000);
    }

}
