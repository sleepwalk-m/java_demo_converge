package com.mask.task.compoent;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.PlainText;

/**
 * @author: Mask.m
 * @create: 2021/07/17 09:35
 * @description: 下载组件定义
 */
@Component
public class JdDownloader implements Downloader {


    @Value("${indexUrl}")
    private String indexUrl;

    private RemoteWebDriver driver;

    public JdDownloader(){
        // 加载chromedriver 是使用chorme的必要条件
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Google\\Chrome\\Application\\chromedriver.exe");
        // 添加chrome的配置信息
        ChromeOptions chromeOptions = new ChromeOptions();
        // 设置为无头模式
        //chromeOptions.addArguments("--headless");
        // 设置打开的窗口大小 非必要属性
        chromeOptions.addArguments("--window-size=1920,1080");

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
        /*
            1. 详情页的下载
            2. 翻页的下载
            3. 列表页下载

         */
        // 1. 列表页
        if ("list".equals(request.getExtra("level"))) {
            try {
                driver.get(indexUrl);
                driver.findElementByCssSelector("#key").sendKeys("手机");
                Thread.sleep(1000);
                driver.findElementByCssSelector(".button").click();
                Thread.sleep(1000);

                // 页面滚动到下方
                Integer start = 0;
                Integer end = 500;
                //6000为最大值
                while (true){
                    if (end == 6000){
                        break;
                    }
                    String scriptStr = "window.scrollTo("+ start + ","+ end +")";
                    driver.executeScript(scriptStr);
                    Thread.sleep(500);
                    start+=500;
                    end+=500;
                }

                // 拿到网页源代码
                String htmlStr = driver.getPageSource();
                // 创建page对象
                return createPage(htmlStr,driver.getCurrentUrl(),"list",request.getExtra("pageNum").toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 2. 分页下载
        if ("page".equals(request.getExtra("level"))) {
            try {
                driver.get(indexUrl);
                driver.findElementByCssSelector("#key").sendKeys("手机");
                Thread.sleep(1000);
                driver.findElementByCssSelector(".button").click();
                Thread.sleep(1000);


                // 这里再去点击分页 3
                String pageNum = request.getExtra("pageNum").toString();
                for (int i = 1; i < Integer.valueOf(pageNum); i++) {
                    // 点击翻页
                    driver.findElementByCssSelector(".fp-next").click();
                    Thread.sleep(1000);
                }


                // 页面滚动到下方
                Integer start = 0;
                Integer end = 500;
                //6000为最大值
                while (true){
                    if (end == 6000){
                        break;
                    }
                    String scriptStr = "window.scrollTo("+ start + ","+ end +")";
                    driver.executeScript(scriptStr);
                    Thread.sleep(500);
                    start+=500;
                    end+=500;
                }

                // 拿到网页源代码
                String htmlStr = driver.getPageSource();
                // 创建page对象
                return createPage(htmlStr,driver.getCurrentUrl(),"list",request.getExtra("pageNum").toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 3. 详情页下载
        if ("detail".equals(request.getExtra("level"))){
            driver.get(request.getUrl());

            String htmlStr = driver.getPageSource();
            return createPage(htmlStr,driver.getCurrentUrl(),"detail",request.getExtra("pageNum").toString());
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
    private Page createPage(String htmlStr, String currentUrl,String flag,String pageNum) {
        Page page = new Page();
        // 设置网页源码 + url
        page.setRawText(htmlStr);
        page.setUrl(new PlainText(currentUrl));
        page.isDownloadSuccess();

        // 设置request对象
        Request request = new Request(currentUrl);
        request.putExtra("level",flag);
        request.putExtra("pageNum",pageNum);
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
