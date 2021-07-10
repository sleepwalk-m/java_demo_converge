package com.mask.crawler.compoent;

import com.mask.crawler.mapper.ItemMapper;
import com.mask.crawler.pojo.Item;
import com.mask.crawler.service.ItemService;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

/**
 * @author: Mask.m
 * @create: 2021/07/10 14:23
 * @description: 爬虫逻辑
 */
@Component
//@ConfigurationProperties(prefix = "baseUrl")
@Setter
public class Crawler {

    @Value("${baseUrl.url}")
    private String url;

    public void doCrawler() {
        try {
            // 1. 获取连接
            CloseableHttpClient httpClient = HttpClientFactory.create();
            // 2. 建立get请求
            HttpGet httpGet = new HttpGet(new URI(url));
            // 这里去添加了header，模拟浏览器，避免跳转到登录页面或者拿到的html不是商品列表页
            setHeader(httpGet);

            // 3. 发起请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            // 4. 获取响应HTML
            String html = EntityUtils.toString(response.getEntity(), "utf-8");
            // 5. 使用jsoup解析html
            parseHtml(html);
            // 6. 关流
            response.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Autowired
    private ItemService itemService;

    /**
     * 使用jsoup解析html
     *
     * @param html
     */
    private void parseHtml(String html) {
        // 1. jsoup解析获取document对象
        Document document = Jsoup.parse(html);
        // 2. 拿到所需要的数据
        // 使用css选择器获取所需数据
        Elements elements = document.select("div#J_goodsList > ul > li");
        for (Element element : elements) {
            String spu = element.select("li").attr("data-spu");
            String sku = element.select("li").attr("data-sku");
            String title = element.select("div.p-img > a").attr("title");
            String price = element.select("div.p-price > strong > i").text();
            // http://img10.360buyimg.com/n7/jfs/t1/188842/2/11020/172673/60dc4c95E823035b8/043fd19f559e8750.jpg
            String picUrl = "http:" + element.select("div.p-img > a > img").attr("data-lazy-img");
            String pic = downloadPic(picUrl);// 下载图片到本地磁盘
            String url = element.select("div.p-img > a").attr("href");

            Item item = new Item();
            item.setSpu(StringUtils.isBlank(spu) ? null :Long.valueOf(spu));
            item.setSku(StringUtils.isBlank(sku) ? null :Long.valueOf(sku));
            item.setTitle(title);
            item.setPrice(StringUtils.isBlank(price) ? 0 :Float.valueOf(price));
            item.setPic(pic);
            item.setUrl(url);
            item.setCreated(new Date());
            item.setUpdated(new Date());

            itemService.saveData(item);
        }


    }

    /**
     * 下载图片到本地
     *
     * @param picUrl
     */
    private String downloadPic(String picUrl) {
        try {
            // 1. 获取hc对象
            CloseableHttpClient httpClient = HttpClientFactory.create();
            // 2. 建立get请求
            HttpGet httpGet = new HttpGet(picUrl);
            // 3. 发起请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            // 4. 下载到本地
            // 4.1 拿到文件后缀
            String suffix = picUrl.substring(picUrl.lastIndexOf("."));
            // 4.2 拼接文件名
            String fileName = UUID.randomUUID() + suffix;
            // 4.3 用字节流写出
            FileOutputStream outputStream = new FileOutputStream(new File("D:\\workspace\\work_demo\\crawler-jd\\image\\" + fileName));
            response.getEntity().writeTo(outputStream);

            // 5. 关流
            outputStream.close();
            response.close();

            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 给请求添加header
     *
     * @param httpGet
     */
    private void setHeader(HttpGet httpGet) {


        httpGet.setHeader(":authority","list.jd.com");
        httpGet.setHeader(":method","GET");
        httpGet.setHeader(":path","/list.html?cat=9987,653,655");
        httpGet.setHeader(":scheme","https");
        httpGet.setHeader("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        httpGet.setHeader("accept-encoding","gzip, deflate, br");
        httpGet.setHeader("accept-language","zh-CN,zh;q=0.9");
        httpGet.setHeader("cache-control","max-age=0");
        httpGet.setHeader("cache-control","max-age=0");
        httpGet.setHeader("referer","https://aq.jd.com/");
        httpGet.setHeader("upgrade-insecure-requests","1");

        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36");
        httpGet.setHeader("cookie","__jdu=1620025880694646238594; shshshfpa=600ae6e9-f863-2eac-2e21-92a6dfbfed06-1620025882; shshshfpb=u5ZhW4%2FkPyEj7iycZrMSe5g%3D%3D; __jdv=76161171|direct|-|none|-|1625897868002; areaId=15; ipLoc-djd=15-1213-2963-0; PCSYCityID=CN_330000_330100_330104; __jdc=122270672; __jda=122270672.1620025880694646238594.1620025880.1625897868.1625900409.3; wlfstk_smdl=8o39uevm39byqn63v3bvxfyka07j7htf; cid=NWFVMjU4NnhTMTMwN25YODEwOHhNMzcwMWJWMDUwMmtTMzUyM21MNDU2NG9XNjM0; thor=54F6739344A584E78AFB4FBE09E502E3C8795C9B8ED98B289C5AB936870E89D56A55D37C987E3A2312CC5E54B223630C439E2F45902BFEDE21CBD914965E96C5F2520707A7E78981CB0F7369FF31E0C6AEE12D2353E7B23818624892C3EDFF77CBCEF62FF46A39FCF64ECB5CF2E008B366A58A9AD5891FF3ADB93C37B8CD9ADEFBC7CEA17B0EA5B1285E5D4BF769A9A6; pinId=uq40PMp4c8f8GY_ZW48_nA; pin=jf_129334; unick=SleepWalk%E4%B8%B6; ceshi3.com=000; _tp=69f7Cjgzx%2BP1%2FreTQPhWVQ%3D%3D; _pst=jf_129334; 3AB9D23F7A4B3C9B=JLDTTUQ3YKWJQNQACVDF57LYTDHF4STZZ6DBW47YZN5X7V3TDSAVIZIJJBZUMJLTMV3RJ424M6KEVC3U4JQ5FG3RJA; __jdb=122270672.19.1620025880694646238594|3.1625900409; shshshfp=7bbbd61b5b406c99243b68623ea4cddd; shshshsID=7c094a460406b4f09ffee9e36954e05d_3_1625901186299");
    }


}
