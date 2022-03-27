package com.gov.encrypt.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author: Mask.m
 * @create: 2022/03/27 12:32
 * @description: 验证码工具类
 */
public class VerifyCodeUtils {

    /**
     * 根据传入的字节数组 校验出验证码
     * @param image
     * @return
     * @throws Exception
     */
    public static String verifyCode(byte[] image) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 发起打码验证请求
        HttpPost httpPost = new HttpPost("http://api.dididati.com/v3/upload/base64");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("image", new String(Base64.getEncoder().encode(image))));
        list.add(new BasicNameValuePair("userkey", "aaa"));// todo 此处需要设置你自己的key 注册滴滴答题 充值1元获取
        HttpEntity httpEntity = new UrlEncodedFormEntity(list, "utf-8");// 注意：尽量指定编码，否则会出现请求失败，获取不到数据
        httpPost.setEntity(httpEntity);
        setPostHeaders(httpPost);
        // 执行得到结果
        CloseableHttpResponse postResponse = httpClient.execute(httpPost);
        return EntityUtils.toString(postResponse.getEntity(), "utf-8");
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
}
