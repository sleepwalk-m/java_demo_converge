package com.mask.puppeteer.compoent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mask.puppeteer.util.PuppeteerUtil;
import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.selector.PlainText;

import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2021/07/17 09:35
 * @description: 下载组件定义
 */
@Component
public class JdDownloader extends HttpClientDownloader {

    @Autowired
    private PuppeteerUtil puppeteerUtil;

    /**
     * 自定义下载
     *
     * @param request
     * @param task
     * @return
     */
    @Override
    public Page download(Request request, Task task) {
        String url = request.getUrl();
        String level = request.getExtra("level").toString();
        Page page = null;
        if ("list".equals(level)) {
            try {
                // 调用puppeteer下载
                String rawPage = puppeteerUtil.getRawpage(url,"list");

                JSONObject jsonObject = (JSONObject) JSONObject.parseObject(rawPage).get("data");
                String pageHtml = jsonObject.getString("result");
                page = createPage(pageHtml, url, "list");
                return page;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if ("detail".equals(level)){
            try {
                String rawPage = puppeteerUtil.getRawpage(url,"detail");

                JSONObject jsonObject = (JSONObject) JSONObject.parseObject(rawPage).get("data");
                String pageHtml = jsonObject.getString("result");
                page = createPage(pageHtml, url, "detail");
                return page;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return page;
    }

    /**
     * 通用的创建page对象的方法
     *
     * @param htmlStr
     * @param currentUrl
     * @return
     */
    private Page createPage(String htmlStr, String currentUrl,String flag) {
        Page page = new Page();
        // 设置网页源码 + url
        page.setRawText(htmlStr);
        page.setUrl(new PlainText(currentUrl));
        page.isDownloadSuccess();

        // 设置request对象
        Request request = new Request(currentUrl);
        request.putExtra("level",flag);
        page.setRequest(request);

        return page;
    }

    @Override
    public void setThread(int i) {

    }
}

