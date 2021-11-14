package com.mask.puppeteer.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2021/11/13 22:26
 * @description: puppeteer工具类
 */
@Component
public class PuppeteerUtil {

    private static final String SERVER_URL = "server_url";
    private static final String DOMAIN = "http://localhost:9876";
    private static final String LEVEL = "level";

    public String getRawpage(String param,String level) throws Exception {
        URI uri = new URIBuilder(DOMAIN).setParameter(SERVER_URL,param).setParameter(LEVEL,level).build();

        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);

        HttpResponse response = httpClient.execute(httpGet);

        return EntityUtils.toString(response.getEntity(), "utf-8");
    }
}
