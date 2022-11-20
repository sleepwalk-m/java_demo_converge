package com.mask.cultural;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2022/11/20 20:51
 * @description: 爬虫的启动
 */
public class CulturalCrawlerSpider {
    public static void main(String[] args) {

        // http://gl.ncha.gov.cn/#/Industry/industry-supervision-management-search
        String url = "http://gl.ncha.gov.cn:9200/api/data/prcperson/portal/one";
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.POST);
        postHeader(request);

        String param = "{\"pcrpersonId\":\"0153FC05-7E06-436D-AF18-71F87052A528\"}";
        request.setRequestBody(HttpRequestBody.json(param, CharsetUtil.UTF_8));

        request.addHeader("pcrpersonId", "0153FC05-7E06-436D-AF18-71F87052A528");

        //secret_time1668949816280pcrpersonId0153FC05-7E06-436D-AF18-71F87052A528crb_2021@#
        String type = "pcrpersonId";
        String userId = "0153FC05-7E06-436D-AF18-71F87052A528";
        Long time = System.currentTimeMillis();
        String secretSign = "secret_time" + time + type + userId + "crb_2021@#";
        String sign = DigestUtil.md5Hex(secretSign);

        request.addHeader("secretSign",sign);
        request.addHeader("secretTime",time.toString());

        Map<String, Object> extra = new HashMap<>();
        extra.put("pageLevel", "getData");
        request.setExtras(extra);

        Spider.create(new CulturalCrawlerPageProcessor())
                .addRequest(request)
                .thread(1)
                .start();
    }


    public static void postHeader(Request request) {

        request.addHeader("Accept", "application/json, text/plain, */*");
        request.addHeader("Accept-Encoding", "identity");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        request.addHeader("Host", "gl.ncha.gov.cn:9200");
        request.addHeader("Origin", "http://gl.ncha.gov.cn");
        request.addHeader("appCode", "cms");
        request.addHeader("ClassCode", "PcrunitQualicationRangeCode");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("Content-Type", "application/json");
        request.addHeader("dataType", "DataPrcpersonTypeRelevance");
        request.addHeader("qualification", "");
        request.addHeader("range", "");
        request.addHeader("secretDefinite", "pcrpersonId");
        request.addHeader("range", "");

        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
        request.addHeader("Referer", "http://gl.ncha.gov.cn/");

    }
}
