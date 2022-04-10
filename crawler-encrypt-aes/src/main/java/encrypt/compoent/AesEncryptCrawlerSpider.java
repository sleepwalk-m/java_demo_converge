package encrypt.compoent;

import cn.hutool.core.util.CharsetUtil;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2022/03/27 15:07
 * @description: 爬虫的启动
 */
public class AesEncryptCrawlerSpider {
    public static void main(String[] args) throws Exception {


        /*
         本期站点：
         http://jzsc.mohurd.gov.cn/data/person

          */
        String name = "张伟";
        String url = "http://jzsc.mohurd.gov.cn/api/webApi/dataservice/query/staff/list?ry_name=" + name + "&pg=0&pgsz=15&total=0";
        Request request = new Request(url);
        getHeader(request);


        Map<String, Object> extra = new HashMap<>();
        extra.put("pageLevel", "getData");
        request.setExtras(extra);

        Spider.create(new AesEncryptCrawlerPageProcessor())
                .addRequest(request)
                .thread(1)
                .start();
    }


    public static void getHeader(Request request){

        request.addHeader("Accept","application/json, text/plain, */*");
        request.addHeader("Accept-Encoding","gzip, deflate");
        request.addHeader("Accept-Language","zh-CN,zh;q=0.9");
        request.addHeader("Host","jzsc.mohurd.gov.cn");
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36");
        request.addHeader("Referer","http://jzsc.mohurd.gov.cn/data/person");

    }
}
