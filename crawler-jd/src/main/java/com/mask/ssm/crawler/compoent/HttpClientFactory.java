package com.mask.ssm.crawler.compoent;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.stereotype.Component;

/**
 * @author: Mask.m
 * @create: 2021/07/10 14:20
 * @description: 返回HC连接对象
 */
@Component
public class HttpClientFactory {

    public static CloseableHttpClient create(){

        // 使用连接池
        return HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager()).build();
    }
}
