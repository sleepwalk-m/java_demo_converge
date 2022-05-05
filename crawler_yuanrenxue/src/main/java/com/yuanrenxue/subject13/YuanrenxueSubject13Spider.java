package com.yuanrenxue.subject13;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2022/04/15 21:58
 * @description:
 */
public class YuanrenxueSubject13Spider implements PageProcessor {
    @Override
    public void process(Page page) {

        Map<String, Object> extra = page.getRequest().getExtras();
        String level = extra.get("level").toString();

        switch (level) {
            case "first":
                parseIndex(page, extra);
                break;
            case "second":
                parseData(page, extra);
                break;
        }

    }

    /**
     * 解析数据
     *
     * @param page
     * @param extra
     */
    private void parseData(Page page, Map<String, Object> extra) {

        String cookieStr = page.getHeaders().get("Set-Cookie").get(0);
        String setCookie = cookieStr.split("expire")[0];

        String s = page.getRawText().split("cookie=")[1].split(";path=/")[0];
        String yuanrenxue_cookie = s.replaceAll("\\('","").replaceAll("'\\)","").replaceAll("\\+","").replaceAll("'","");


        String cookie = setCookie + yuanrenxue_cookie;
        String url = "https://match.yuanrenxue.com/api/match/13";
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.GET);
        request.putExtra("level", "second");
        request.putExtra("result", "0");

        //request.addHeader("user-agent", "yuanrenxue.project");
        request.addHeader("cookie", yuanrenxue_cookie);
        //request.addCookie("yuanrenxue_cookie", yuanrenxue_cookie.split("=")[1]);
        //request.addCookie("sessionid", setCookie.split("=")[1]);

        //request.addHeader("Host","match.yuanrenxue.com");
        //request.addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36");
        page.addTargetRequest(request);
    }

    /**
     * 发起题目的分页请求
     *
     * @param page
     * @param extra
     */
    private void parseIndex(Page page, Map<String, Object> extra) {

        String cookieStr = page.getHeaders().get("Set-Cookie").get(0);
        String setCookie = cookieStr.split("expire")[0];

        String s = page.getRawText().split("cookie=")[1].split(";path=/")[0];
        String yuanrenxue_cookie = s.replaceAll("\\('","").replaceAll("'\\)","").replaceAll("\\+","").replaceAll("'","");


        String cookie = setCookie + yuanrenxue_cookie;
        String url = "https://match.yuanrenxue.com/match/13";
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.GET);
        request.putExtra("level", "second");
        request.putExtra("result", "0");

        //request.addHeader("user-agent", "yuanrenxue.project");
        request.addHeader("cookie", setCookie);
        //request.addCookie("yuanrenxue_cookie", yuanrenxue_cookie.split("=")[1]);
        //request.addCookie("sessionid", setCookie.split("=")[1]);

        //request.addHeader("Host","match.yuanrenxue.com");
        //request.addHeader("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36");
        page.addTargetRequest(request);
    }

    @Override
    public Site getSite() {
        return Site.me().addCookie("sessionid","twp02wokgp6yd362s1rbfmv74tzgirf4");
    }

    public static void main(String[] args) {


        Request request = new Request("https://match.yuanrenxue.com/match/13");
        request.putExtra("level", "first");
        //request.addHeader("cookie","sessionid=pblnv2eyk2xr1j9syekgdv4x9ps4kohw;");
        Spider.create(new YuanrenxueSubject13Spider())
                .addRequest(request)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new DonothingDuplicateRemover())
                )
                .thread(1)
                .start();
    }
}
