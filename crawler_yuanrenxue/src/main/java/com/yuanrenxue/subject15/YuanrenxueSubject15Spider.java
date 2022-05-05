package com.yuanrenxue.subject15;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yuanrenxue.subject12.YuanrenxueSubject12Spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2022/04/15 21:58
 * @description:
 */
public class YuanrenxueSubject15Spider implements PageProcessor {
    @Override
    public void process(Page page) {

        Map<String,Object> extra = page.getRequest().getExtras();
        String level = extra.get("level").toString();

        switch (level){
            case "first":
                parseIndex(page,extra);
                break;
            case "second":
                parseData(page,extra);
                break;
        }

    }

    /**
     * 解析数据
     * @param page
     * @param extra
     */
    private void parseData(Page page, Map<String, Object> extra) {

        JSONObject obj = JSONObject.parseObject(page.getRawText());
        JSONArray data = obj.getJSONArray("data");
        int result = Integer.valueOf(extra.get("result").toString());
        for (Object val : data) {
            JSONObject values = (JSONObject)val;
            result += values.getInteger("value");
        }


        String pageNum = extra.get("pageNum").toString();

        String url = "https://match.yuanrenxue.com/api/match/12?page="+pageNum+"&m=" + Base64.encode("yuanrenxue"+pageNum);
        Request request = new Request(url);
        request.putExtra("pageNum",Integer.valueOf(pageNum) + 1);
        request.putExtra("level","second");
        request.putExtra("result",result);


        request.addHeader("user-agent","yuanrenxue.project");
        request.addHeader("cookie","你自己的sessionid");

        page.addTargetRequest(request);
    }

    /**
     * 发起题目的分页请求
     * @param page
     * @param extra
     */
    private void parseIndex(Page page, Map<String, Object> extra) {

        String pageNum = extra.get("pageNum").toString();

        String url = "https://match.yuanrenxue.com/api/match/12?page="+pageNum+"&m=" + Base64.encode("yuanrenxue"+pageNum);
        Request request = new Request(url);
        request.putExtra("pageNum",Integer.valueOf(pageNum) + 1);
        request.putExtra("level","second");
        request.putExtra("result","0");

        request.addHeader("user-agent","yuanrenxue.project");
        request.addHeader("cookie","你自己的sessionid");

        page.addTargetRequest(request);
    }

    @Override
    public Site getSite() {
        return Site.me();
    }

    public static void main(String[] args) {


        Request request = new Request("https://www.baidu.com");
        request.putExtra("level","first");
        request.putExtra("pageNum","1");
        request.putExtra("result","0");

        Spider.create(new YuanrenxueSubject12Spider())
                .addRequest(request)
                .thread(1)
                .start();
    }
}
