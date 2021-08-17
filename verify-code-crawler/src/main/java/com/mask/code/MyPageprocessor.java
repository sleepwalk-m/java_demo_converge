package com.mask.code;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * @author: Mask.m
 * @create: 2021/08/17 20:16
 * @description:
 */
public class MyPageprocessor implements PageProcessor {


    /*
        1. 生成验证码
        2. 验证你输入的验证码 --》 请求
        3. 你真正要查询的数据接口--》请求

     */


    // 验证码
    private static final String VERIFY_CODE_URL = "https://app.jszg.edu.cn/public/verifyCode.jpg?_{time}";
    // 验证 验证码
    private static final String RECO_CODE_URL = "https://app.jszg.edu.cn/portal/cert/query/save";


    // 数据请求
    private static final String QUERY_DATA_URL = "https://app.jszg.edu.cn/portal/cert/query/certinfo?_idNo={idNum}&_certNo={certNum}&_={time}";

    @SneakyThrows
    public void process(Page page) {
        String url = page.getRequest().getUrl();
        Map<String, Object> map = page.getRequest().getExtras();
        String level = map.get("level").toString();

            switch (level) {
                case "verifyCode":
                    parseVerifyCode(page, map);
                    break;
                case "codeCheck":
                    parseVerifyCodeCheck(page, map);
                    break;
                case "queryData":
                    parseQueryData(page, map);
                    break;

            }
    }

    /**
     * 解析数据
     *
     * @param page
     * @param extra
     */
    public void parseQueryData(Page page, Map<String, Object> extra) {
        page.putField("data",page.getRawText());
    }

    /**
     * 验证验证码请求，并请求数据
     *
     * @param page
     * @param extra
     */
    public void parseVerifyCodeCheck(Page page, Map<String, Object> extra) throws MalformedURLException {
        Selectable selectable = page.getHtml().$("response");
        String certexist = selectable.xpath("///@certexist").get();
        String success = selectable.xpath("///@success").get();

        if (certexist.equals("false")) {
            // 表示不存在
        }


        if (/*certexist.equals("true") &&*/ success.equals("true")) {
            // 请求时间戳 需要作为参数拼接
            String dataUrl = QUERY_DATA_URL.replace("{idNum}", "11").replace("{certNum}", "111").replace("{time}", System.currentTimeMillis()+"");
            Request request = new Request(dataUrl);
            request.setMethod(HttpConstant.Method.GET);
            Map<String, Object> map = new HashMap<>();
            map.putAll(extra);
            map.put("level", "queryData");
            setGetHeaders(request);
            request.setExtras(map);
            page.addTargetRequest(request);
        } else {
            // 重新请求验证码
            fillInVerifyCode(page, extra);
        }
    }

    /**
     * 调用打码服务 并请求验证验证码
     *
     * @param page
     * @param extra
     */
    public void parseVerifyCode(Page page, Map<String, Object> extra) throws Exception {
        // 调用验证码服务
        String result = verifyCodeServer(page);
        // 得到验证码
        JSONObject jsonObject = JSON.parseObject(result);
        if (jsonObject.get("err").equals("0")){
            //组装填写验证码请求
            String code = JSONPath.eval(jsonObject, "result.code").toString();
            Request request = new Request(RECO_CODE_URL);
            request.setMethod(HttpConstant.Method.POST);
            setPostHeaders(request);
            Map<String, Object> param = new HashMap<>();
            param.put("__captcha_id", code);
            param.put("name", "111");
            param.put("idNo", "!11");
            param.put("certNo", "111");
            Map<String, Object> map = new HashMap<>();
            map.putAll(extra);
            map.put("level", "codeCheck");
            request.setRequestBody(HttpRequestBody.form(param,"UTF-8"));;
            request.setExtras(map);
            page.addTargetRequest(request);
            return;
        } else {
            //继续请求验证码图片
            fillInVerifyCode(page, extra);
        }
    }

    /**
     * 验证码服务
     * @param page
     * @return
     * @throws Exception
     */
    private String verifyCodeServer(Page page) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 发起打码验证请求
        HttpPost httpPost = new HttpPost("http://api.dididati.com/v3/upload/base64");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("image", new String(Base64.getEncoder().encode(page.getBytes()))));
        list.add(new BasicNameValuePair("userkey", "ffce95e5c573089d3f65c6b1407c2968a60ee839a0a57a96"));
        HttpEntity httpEntity = new UrlEncodedFormEntity(list, "utf-8");// 注意：尽量指定编码，否则会出现请求失败，获取不到数据
        httpPost.setEntity(httpEntity);
        setPostHeaders(httpPost);
        // 执行得到结果
        CloseableHttpResponse postResponse = httpClient.execute(httpPost);
        return EntityUtils.toString(postResponse.getEntity(), "utf-8");
    };

    /**
     * 请求验证码
     */
    public void fillInVerifyCode(Page page, Map<String, Object> extra) throws MalformedURLException {
        // 拼接验证码请求请求
        String verifyCodeUrl = VERIFY_CODE_URL.replace("{time}", System.currentTimeMillis() + "");
        Request request = new Request(verifyCodeUrl);
        setGetHeaders(request);
        Map<String, Object> map = new HashMap<>();
        map.putAll(extra);
        map.put("level", "verifyCode");
        request.setExtras(map);
        page.addTargetRequest(request);
    }


    /**
     * 设置请求头
     */
    public void setGetHeaders(Request request) throws MalformedURLException {
        request.addHeader("Accept", "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8");
        request.addHeader("Accept-Encoding", "gzip, deflate");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("Host", new URL(request.getUrl()).getAuthority());
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");
        request.addHeader("Upgrade-Insecure-Requests", "1");
        //request.addHeader("Cookie", "Hm_lvt_dc1d69ab90346d48ee02f18510292577=1628579042,1628582440; Hm_lpvt_dc1d69ab90346d48ee02f18510292577=1628583702");
        //request.addHeader("Referer", "http://cjcx.neea.edu.cn/");
    }


    /**
     * 添加请求伪装
     *
     * @param httpPost
     */
    public static void setPostHeaders(HttpPost httpPost) {
        httpPost.addHeader("Accept", "*/*");
        httpPost.addHeader("Accept-Encoding", "gzip, deflate");
        httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        //httpPost.addHeader("Content-Type","multipart/form-data; boundary=48940923NODERESLTER3890457293");
        httpPost.addHeader("Connection", "close");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:24.0) Gecko/20100101 Firefox/24.0");
        httpPost.addHeader("Host", "api.dididati.com:80");
    }


    /**
     * 设置请求头
     */
    public void setPostHeaders(Request request) throws MalformedURLException {
        request.addHeader("Accept", "*/*");
        request.addHeader("Accept-Encoding", "gzip, deflate");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        request.addHeader("Host", new URL(request.getUrl()).getAuthority());
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");
        request.addHeader("Upgrade-Insecure-Requests", "1");
        //request.addHeader("Cookie", "Hm_lvt_dc1d69ab90346d48ee02f18510292577=1628579042,1628582440; Hm_lpvt_dc1d69ab90346d48ee02f18510292577=1628583702");
        //request.addHeader("Referer", "http://cjcx.neea.edu.cn/");
    }

    public Site getSite() {
        return Site.me();
    }
}
