package com.gov.encrypt.compoent;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.net.URLEncoder;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gov.encrypt.util.VerifyCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.*;

/**
 * @author: Mask.m
 * @create: 2022/03/27 12:38
 * @description:
 */
public class GovCrawlerPageProcessor implements PageProcessor {


    @Override
    public void process(Page page) {
        Map<String, Object> extras = page.getRequest().getExtras();
        String level = extras.get("pageLevel").toString();
        switch (level){
            case "image":
                parseImage(page,extras);
                break;
            case "verifyCode":
                verifyCode(page,extras);
                break;
            case "getData":
                getData(page,extras);
                break;
        }

    }

    /**
     * 获取数据
     * @param page
     * @param extras
     */
    private void getData(Page page, Map<String, Object> extras) {
        String rawText = page.getRawText();
        System.out.println("rawText = " + rawText);
    }

    /**
     * 验证验证码，发起数据请求
     * @param page
     * @param extras
     */
    private void verifyCode(Page page, Map<String, Object> extras) {
        String rawText = page.getRawText();
        Boolean success = JSON.parseObject(rawText).getBoolean("success");
        if (success){
            // 发起数据请求
            String url = "http://app.gjzwfw.gov.cn/jimps/link.do";
            Request request = new Request(url);
            request.setMethod(HttpConstant.Method.POST);
            setPostHeaders(request);
            request.addHeader("cookie",extras.get("cookie").toString());
            String requestTime = new Date().getTime() + "";
            String sign = SecureUtil.md5("zcaqgcszs" + requestTime);
            String cert1 = "11";
            String cert2 = "22";

            String param = "{\"from\":\"1\",\"key\":\"8c7196339f6c4e3993df2667e2b64eee\",\"requestTime\":\"" + requestTime + "\",\"sign\":\"" + sign + "\",\"access_token\":\"4bdcd791b46f0b0d61629876ddb35947\",\"param\":{\"condition\":[{\"dataCode\":\"ZHIYEZHENGHAO\",\"dataValue\":\""+cert1+"\",\"operation\":\"eq\"},{\"dataCode\":\"ZHIYEZIGEZHENGHAO\",\"dataValue\":\"" +cert2+ "\",\"operation\":\"eq\"}],\"sid\":\"15820AL2A7015A63\"}}";

            // 设置参数
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("param",param);
            request.setRequestBody(HttpRequestBody.form(paramMap,CharsetUtil.UTF_8));

            // 设置上下文
            extras.put("pageLevel","getData");// 验证验证码
            request.setExtras(extras);

            page.addTargetRequest(request);
        }else {
         // todo 重新请求验证码 再走流程 = 重试
        }
    }

    /**
     * 处理验证码图片，发起验证码验证
     * @param page
     * @param extras
     */
    private void parseImage(Page page, Map<String, Object> extras) {

        byte[] bytes = page.getBytes();// 验证码图片内容
        String encode = "data:image/png;base64," + Base64.encode(bytes);
        String code = "";
        String cookie = getCookie(page);
        try {
            String raw = VerifyCodeUtils.verifyCode(bytes);
            JSONObject result = JSON.parseObject(raw).getJSONObject("result");
            code = result.getString("code"); // 经过识别之后的结果

            String url = "http://app.gjzwfw.gov.cn/jmopen/checkValiCode.do";
            Request request = new Request(url);
            request.setMethod(HttpConstant.Method.POST);
            request.addHeader("cookie",cookie);
            // 设置参数
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("code",code);
            request.setRequestBody(HttpRequestBody.form(paramMap,CharsetUtil.UTF_8));

            // 设置上下文
            extras.put("pageLevel","verifyCode");// 验证验证码
            extras.put("cookie",cookie);// 验证验证码
            request.setExtras(extras);

            page.addTargetRequest(request);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Site getSite() {
        return Site.me().setTimeOut(30000);
    }



    /**
     * cookie
     * @param page
     * @param
     */
    private static String getCookie(Page page) {
        List<String> list = page.getHeaders().get("Set-Cookie");
        String cookie = "";
        //cookie如果能从上一级拿到
        if (list.size() != 0) {
            for (String responseCookie : list) {
                cookie += responseCookie.replaceAll("path=/", "");
            }
        }
        return cookie;
    }

    /**
     * 添加请求伪装
     *
     * @param httpPost
     */
    public static void setPostHeaders(Request httpPost) {
        httpPost.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate");
        httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:24.0) Gecko/20100101 Firefox/24.0");
        httpPost.addHeader("Host", "app.gjzwfw.gov.cn");
        httpPost.addHeader("Origin", "http://app.gjzwfw.gov.cn");
        httpPost.addHeader("Referer", "http://app.gjzwfw.gov.cn/jmopen/webapp/html5/unZip/73972cc317884de39d7767e1d44bbf82/zcaqgcszs/index.html");
    }
}
