package com.mask.js.job.utils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;

import java.util.List;

/**
 * @author: Mask.m
 * @create: 2023/08/04 23:04
 * @description:
 */
public class HeaderUtils {


    /**
     * 添加请求伪装
     *
     * @param request
     */
    public static void setGetHeaders(Request request) {
        request.addHeader("Accept", "application/json, text/plain, */*");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        request.addHeader("Cache-Control", "no-cache");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("From-Domain", "51job_web");
        request.addHeader("Pragma", "no-cache");
        request.addHeader("Referer", "https://we.51job.com/pc/search?keyword=java&searchType=2&sortType=0&metro=");
        request.addHeader("Sec-Fetch-Dest", "empty");
        request.addHeader("Sec-Fetch-Mode", "cors");
        request.addHeader("Sec-Fetch-Site", "same-origin");
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
        request.addHeader("account-id", "");
        request.addHeader("partner", "");
        request.addHeader("property", "%7B%22partner%22%3A%22%22%2C%22webId%22%3A2%2C%22fromdomain%22%3A%2251job_web%22%2C%22frompageUrl%22%3A%22https%3A%2F%2Fwe.51job.com%2F%22%2C%22pageUrl%22%3A%22https%3A%2F%2Fwe.51job.com%2Fpc%2Fsearch%3Fkeyword%3Djava%26searchType%3D2%26sortType%3D0%26metro%3D%22%2C%22identityType%22%3A%22%22%2C%22userType%22%3A%22%22%2C%22isLogin%22%3A%22%E5%90%A6%22%2C%22accountid%22%3A%22%22%7D");
        request.addHeader("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"");
        request.addHeader("sec-ch-ua-mobile", "?0");
        request.addHeader("sec-ch-ua-platform", "\"Windows\"");
        request.addHeader("sign", "7f972d964661d4b65147a7eba7f0f6b625daad61e18e441174b49f3c3dc99087");
        request.addHeader("user-token", "");
        request.addHeader("uuid", "68000cc512692910ebca2e4b88483fc3");
    }


    public static String addCookie(Page page) {
        StringBuilder sb = new StringBuilder();
        List<String> list = page.getHeaders().get("Set-Cookie");
        List<String> list2 = page.getHeaders().get("set-cookie");
        if (list2 != null && !list2.isEmpty()){
            list.addAll(list2);
        }
        for (String s : list) {
            String s1 = s.split(";")[0];

            String[] split = s1.split("=");
            String cookie = split[0] + "=" + split[1];
            sb.append(cookie).append(";");
        }
        return sb.toString();
    }
}
