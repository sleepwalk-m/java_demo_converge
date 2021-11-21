package com.example.work;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Mask.m
 * @create: 2021/04/01 22:35
 * @description: 测试
 */
public class ArrayTest {

    public static void main(String[] args) throws IOException {

       String domain = "https://himg.bdimg.com/sys/portrait/item/public.1.df425c42.Z1pFsAr0D_6p_klthOU1jA.jpg";

        HttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(domain);

        HttpResponse response = httpClient.execute(httpGet);
        Header[] allHeaders = response.getAllHeaders();
        for (Header allHeader : allHeaders) {
            System.out.println(allHeader.getName() + ":"+allHeader.getValue());
        }
        long contentLength = response.getEntity().getContentLength();
        System.out.println("contentLength = " + contentLength);

    }
}
