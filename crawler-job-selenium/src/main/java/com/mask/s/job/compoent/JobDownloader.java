package com.mask.s.job.compoent;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.selector.PlainText;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2021/07/17 09:35
 * @description: 下载组件定义
 */
public class JobDownloader implements Downloader {

    private RemoteWebDriver driver;
    private HttpClientDownloader httpClientDownloader = new HttpClientDownloader();

    public JobDownloader() {
        // 加载chromedriver 是使用chorme的必要条件
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        // 添加chrome的配置信息
        ChromeOptions chromeOptions = new ChromeOptions();
        // 设置为无头模式
        //chromeOptions.addArguments("--headless");
        // 设置打开的窗口大小 非必要属性
        chromeOptions.addArguments("--window-size=1920,1080");
        //chromeOptions.addArguments("--proxy-server=http://"+"106.13.185.186"+":"+"26090"/*+"#ba65413e9a4c:aa8a3ac7c1"*/);

        // 使用配置信息 创建driver对象
        driver = new ChromeDriver(chromeOptions);
    }


    /**
     * 自定义下载
     *
     * @param request
     * @param task
     * @return
     */
    @Override
    public Page download(Request request, Task task) {
        try {
            /*if (request.getExtra("level").equals("list")){
                return httpClientDownloader.download(request,task);
            }*/
            driver.get(request.getUrl());

            // 拿到网页源代码
            String htmlStr = driver.getPageSource();
            // 创建page对象
            Page page = createPage(htmlStr, driver.getCurrentUrl());
            page.getRequest().getExtras().putAll(request.getExtras());
            //driver.close();
            return page;

        } catch (Exception e) {
            //driver.close();
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通用的创建page对象的方法
     *
     * @param htmlStr
     * @param currentUrl
     * @return
     */
    private Page createPage(String htmlStr, String currentUrl) {
        Page page = new Page();
        // 设置网页源码 + url
        page.setRawText(htmlStr);
        page.setUrl(new PlainText(currentUrl));
        page.isDownloadSuccess();

        // 设置request对象
        Request request = new Request(currentUrl);
        Map<String,Object> map = new HashMap<>();
        request.setExtras(map);
        page.setRequest(request);

        return page;
    }

    /**
     * 设置线程方法 与我们无关 放在这里即可
     *
     * @param i
     */
    @Override
    public void setThread(int i) {

    }
}
