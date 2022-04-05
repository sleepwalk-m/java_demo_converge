package com.des.encrypt.compoent;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.des.encrypt.util.DESEncryptUtil;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2022/03/27 15:07
 * @description: 爬虫的启动
 */
public class DesEncryptCrawlerSpider {
    public static void main(String[] args) throws Exception {


        /*
         本期站点：
         https://jdcjcwx.jtzyzg.org.cn/JDCJCWX/LEAP/jdcjcwx/html/publicQuery.html

          */
        String url = "https://jdcjcwx.jtzyzg.org.cn/JDCJCWX/restservices/http/single/query";
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.POST);

        String service = DESEncryptUtil.encrypt("JDC_getExamScore");
        String version = DESEncryptUtil.encrypt("1.0.0");
        String data = DESEncryptUtil.encrypt("{\"bean\":{\"personname\":\"22222\",\"cardno\":\"22222\"}}");
        String param = "{\"service\":\""+ service +"\",\"version\":\" "+version+"\",\"data\":\""+data+"\"}";
        request.setRequestBody(HttpRequestBody.json(param, CharsetUtil.UTF_8));

        Map<String, Object> extra = new HashMap<>();
        extra.put("pageLevel", "getData");
        request.setExtras(extra);

        Spider.create(new DesEncryptCrawlerPageProcessor())
                .addRequest(request)
                .thread(1)
                .start();
    }
}
