package com.mask.code;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2021/08/17 19:51
 * @description:
 */
public class MySpider {

    /*
        1. 生成验证码
        2. 验证你输入的验证码 --》 请求
        3. 你真正要查询的数据接口--》请求

     */
    // 验证码
    private static final String VERIFY_CODE_URL = "https://app.jszg.edu.cn/public/verifyCode.jpg?_{time}";

    public static void main(String[] args) {
        // 拼接验证码请求请求
        String verifyCodeUrl = VERIFY_CODE_URL.replace("{time}",""+System.currentTimeMillis());
        Request request = new Request(verifyCodeUrl);
        //setGetHeaders(request);
        Map<String, Object> map = new HashMap<>();
        map.put("level", "verifyCode");
        request.setExtras(map);

        Spider.create(new MyPageprocessor())
                .addRequest(request)
                .addPipeline(new ConsolePipeline())
                .addPipeline(new FilePipeline("D:\\data"))
                .start();
    }
}
