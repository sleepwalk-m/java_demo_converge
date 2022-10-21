package com.mask.s.job.compoent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.Scheduler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2021/07/14 20:56
 * @description: 爬虫启动
 */
@Component
public class JobSpider {

    private static final String URL = "https://search.51job.com/list/080200,000000,0000,00,9,99,java,2,2.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";

    public static void main(String[] args){


        Request request = new Request(URL);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("level","list"); // 标识位
        request.setExtras(map);
        setGetHeaders(request);
        request.addHeader("Accept","application/json, text/javascript, */*; q=0.01");

        Spider.create(new JobPageProcessor())
                .addPipeline(new JobPipeline())
                .setDownloader(new JobDownloader())
                .addRequest(request)
                .start();
    }


    /**
     * 添加请求伪装
     *
     * @param request
     */
    public static void setGetHeaders(Request request) {
        request.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        request.addHeader("Accept-Encoding", "gzip, deflate, br");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        request.addHeader("Cache-Control", "max-age=0");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("Host", "jobs.51job.com");
        request.addHeader("Referer", "https://search.51job.com/");
        request.addHeader("Upgrade-Insecure-Requests", "1");
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        request.addHeader("cookie", "_uab_collina=164697140224744622087697; guid=68000cc512692910ebca2e4b88483fc3; nsearch=jobarea%3D%26%7C%26ord_field%3D%26%7C%26recentSearch0%3D%26%7C%26recentSearch1%3D%26%7C%26recentSearch2%3D%26%7C%26recentSearch3%3D%26%7C%26recentSearch4%3D%26%7C%26collapse_expansion%3D; 51job=cuid%3D135427528%26%7C%26cusername%3DkR0zqGBUwsJ21RBVsr7IrV6QnDUpssj7I9RHuRbAOHo%253D%26%7C%26cpassword%3D%26%7C%26cname%3DC4s7gOjDziCFEBwn38nIIw%253D%253D%26%7C%26cemail%3D9%252BHyMY7NfA9cKB6f4EjaMu2JKkOvVqXybItUpoY0EpU%253D%26%7C%26cemailstatus%3D3%26%7C%26cnickname%3D%26%7C%26ccry%3D.0gtvb.ae5XXM%26%7C%26cconfirmkey%3DjirwMYm783yKE%26%7C%26cautologin%3D1%26%7C%26cenglish%3D0%26%7C%26sex%3D0%26%7C%26cnamekey%3DjinFgXciPS3Q6%26%7C%26to%3D79571c03a0b0fd50d71fa00e004d4b36622aced5%26%7C%26; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22135427528%22%2C%22first_id%22%3A%22183a3bab53c2e3-05d1a5663075fe-26021c51-1327104-183a3bab53dcd3%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E8%87%AA%E7%84%B6%E6%90%9C%E7%B4%A2%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC%22%2C%22%24latest_referrer%22%3A%22https%3A%2F%2Fwww.baidu.com%2Flink%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTgzYTNiYWI1M2MyZTMtMDVkMWE1NjYzMDc1ZmUtMjYwMjFjNTEtMTMyNzEwNC0xODNhM2JhYjUzZGNkMyIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjEzNTQyNzUyOCJ9%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22135427528%22%7D%2C%22%24device_id%22%3A%22183a3bab53c2e3-05d1a5663075fe-26021c51-1327104-183a3bab53dcd3%22%7D; partner=sem_pcbaidupz_2; privacy=1666269416; slife=lastlogindate%3D20221020%26%7C%26; acw_tc=ac11000116662694197083637e00e1c45cca243d9ee0d3b880f43ede041187; acw_sc__v2=635140eeb8119382701b056df7773c6bcc5da53b; search=jobarea%7E%60000000%7C%21ord_field%7E%600%7C%21recentSearch0%7E%60000000%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAJava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch1%7E%60000000%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch2%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA04%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%D0%C5%CF%A2%B9%DC%C0%ED%D3%EB%D0%C5%CF%A2%CF%B5%CD%B3%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch3%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%D0%C5%CF%A2%B9%DC%C0%ED%D3%EB%D0%C5%CF%A2%CF%B5%CD%B3%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch4%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21collapse_expansion%7E%601%7C%21; ssxmod_itna=QqmxnDyD07iQTwDlRokDkRmdY5qExDvr3G8KjDBLEW4iNDnD8x7YDvmm=4amYWYWbrYeEe++cGdE4120fTdriGi3PFZox0aDbqGkPjCr4GGjxBYDQxAYDGDDPkDj4ibDY+tODjnz/Zl61KDpxGrDlKDRx0749KDbxDaDGakVCh46O5HY3A+DiyD9ciq6xG1DQ5Dsabf74DCfRf4lOHAw4i3ECIhK40OD0Fwngp+DB4C/h5OySuT5YrNee+epj4ehBh43OGeq0rqMG0PlnGNzD/4h01gZ0tDicG4pN2D4D===; ssxmod_itna2=QqmxnDyD07iQTwDlRokDkRmdY5qExDvr3G8KD8q10xGNmiqGaiKBimk+nkx8hiI4OelxWY2zuet0yGGqd/eEbAVmKZANCd2FcAQIoX8=dOXw1Ft1cKNqVPoCs6ddyC7SjrNtNR/1Zu6gejvVQd0RQnUX0wN5TCjPslQYLwiqaCX3cT6ormbi=D0UeZ0x6CuKyQtNNpuHdctQvQ9Fdn9OH1nkT1ylzqyYXQ7hU0yv3=NwgPjxMg+bBYe0YGwFef=eixG2nowK8Eh+oDA8Uo5QUDC1mg4RE2KC4hDDFqD2WB8iGoCR=iD=");
    }
}
