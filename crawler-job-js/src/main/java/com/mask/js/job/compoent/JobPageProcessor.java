package com.mask.js.job.compoent;

import cn.hutool.script.JavaScriptEngine;
import cn.hutool.script.ScriptUtil;
import com.mask.js.job.pojo.JobInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;

import javax.script.ScriptException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mask.js.job.utils.HeaderUtils.addCookie;

/**
 * @author: Mask.m
 * @create: 2021/07/14 20:15
 * @description:
 */
@Component
public class JobPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page page) {
        // 上下文 可以传递一些数据
        Map<String, Object> extras = page.getRequest().getExtras();
        String level = extras.get("level").toString();

  /*      if (StringUtils.isBlank(level)){
            level = "list";
        }*/

        switch (level) {
            case "index":
                parseIndex(page);
                break;
            case "list":
                parseList(page);
                break;
            case "detail":
                parseDetail(page);
                break;
        }

    }

    /**
     * 解析详情页
     *
     * @param page
     */
    private void parseDetail(Page page) {
        Html html = page.getHtml();
        String companyName = html.$("p.cname > a:first-child").xpath("///allText()").get();
        String companyAddr = html.$("div.tCompany_main > div:nth-of-type(2) p").xpath("///allText()").get();
        String companyInfo = html.$("div.tCompany_main > div:last-child").xpath("///allText()").get();
        String jobName = html.$("div.cn > h1").xpath("///allText()").get();
        String tempJobStr = html.$("p.msg.ltype").xpath("///allText()").get();
        String jobAddr = "";
        String time = "";
        if (StringUtils.isNotBlank(tempJobStr)) {
            String[] split = tempJobStr.split("|");
            if (split.length > 4) {
                jobAddr = split[0];
                time = split[split.length - 1];
            }
        }
        String jobInfoStr = html.$("div.bmsg.job_msg.inbox p").xpath("///allText()").get();
        String tempSalaryStr = html.$("div.cn strong").xpath("///allText()").get();
        String salaryMin = "";
        String salaryMax = "";
        if (StringUtils.isNotBlank(tempSalaryStr)) {
            String[] salarySplit = tempSalaryStr.split("万")[0].split("-");
            if (salarySplit.length > 1) {
                salaryMin = salarySplit[0];
                salaryMax = salarySplit[1];
            }
        }
        String url = page.getUrl().get();

        // 去保存
        JobInfo jobInfo = new JobInfo();
        jobInfo.setCompanyName(companyName);
        jobInfo.setCompanyAddr(companyAddr);
        jobInfo.setCompanyInfo(companyInfo);
        jobInfo.setJobName(jobName);
        jobInfo.setJobAddr(jobAddr);
        jobInfo.setJobInfo(jobInfoStr);
        jobInfo.setUrl(url);
        jobInfo.setTime(time);
        jobInfo.setSalaryMin(Float.valueOf(salaryMin));
        jobInfo.setSalaryMax(Float.valueOf(salaryMax));

        // 交给pipeline来处理
        page.putField("jobInfo", jobInfo);
    }

    /**
     * 解析列表页
     *
     * @param page
     */
    private void parseIndex(Page page) {
        String coo = addCookie(page);
        System.out.println("coo = " + coo);

        String raw = page.getRawText();

        String temp = StringUtils.substringBefore(raw, "';");
        String arg1 = StringUtils.substringAfter(temp, "arg1='");

        String acwCookie = getAcwCookie(arg1);
        String cookie = "acw_sc__v2=" + acwCookie;

        String url = "https://we.51job.com/api/job/search-pc?api_key=51job&timestamp=1691160473&keyword=java&searchType=2&function=&industry=&jobArea=000000&jobArea2=&landmark=&metro=&salary=&workYear=&degree=&companyType=&companySize=&jobType=&issueDate=&sortType=0&pageNum=1&requestId=2a554e056482df976d49dc07aeb0c551&pageSize=20&source=1&accountId=&pageCode=sou%7Csou%7Csoulb";
        Request request = new Request(url);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("level", "list"); // 标识位
        map.put("cookie", cookie); // 标识位
        request.setExtras(map);
        setGetHeaders(request);
        request.addHeader("cookie", coo + cookie);
        page.addTargetRequest(request);
    }

    /**
     * 解析列表页
     *
     * @param page
     */
    private void parseList(Page page) {

        String raw = page.getRawText();

        String temp = StringUtils.substringBefore(raw, "';");
        String arg1 = StringUtils.substringAfter(raw, "arg1='");

        String cookie = getAcwCookie(arg1);


        Json json = page.getJson();
        // 获取所有的目标详情url
        List<String> urlList = json.jsonPath("engine_jds[*].job_href").all();
        for (String s : urlList) {
            Request request = new Request(s);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("level", "detail"); // 标识位
            request.setExtras(map);
            setGetHeaders(request);
            page.addTargetRequest(request);
        }
    }

    public Site getSite() {
        return site;
    }


    /**
     * 添加请求伪装
     *
     * @param request
     */
    public void setGetHeaders(Request request) {
        request.addHeader("Accept", "application/json, text/plain, */*");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
        request.addHeader("Cache-Control", "no-cache");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("From-Domain", "51job_web");
        request.addHeader("Pragma", "no-cache");
        request.addHeader("Referer", "https://we.51job.com/pc/search?keyword=java&searchType=2&sortType=0&metro=");
        request.addHeader("Sec-Fetch-Dest", "empty");
        request.addHeader("Sec-Fetch-Mode", "cors");
        request.addHeader("Sec-Fetch-Site", "same-origin");
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
        request.addHeader("account-id", "");
        request.addHeader("partner", "");
        request.addHeader("property", "%7B%22partner%22%3A%22%22%2C%22webId%22%3A2%2C%22fromdomain%22%3A%2251job_web%22%2C%22frompageUrl%22%3A%22https%3A%2F%2Fwe.51job.com%2F%22%2C%22pageUrl%22%3A%22https%3A%2F%2Fwe.51job.com%2Fpc%2Fsearch%3Fkeyword%3Djava%26searchType%3D2%26sortType%3D0%26metro%3D%22%2C%22identityType%22%3A%22%22%2C%22userType%22%3A%22%22%2C%22isLogin%22%3A%22%E5%90%A6%22%2C%22accountid%22%3A%22%22%7D");
        request.addHeader("sec-ch-ua", "\"Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"115\", \"Chromium\";v=\"115\"");
        request.addHeader("sec-ch-ua-mobile", "?0");
        request.addHeader("sec-ch-ua-platform", "\"Windows\"");
        request.addHeader("sign", "7f972d964661d4b65147a7eba7f0f6b625daad61e18e441174b49f3c3dc99087");
        request.addHeader("user-token", "");
        request.addHeader("uuid", "68000cc512692910ebca2e4b88483fc3");
    }


    public String getAcwCookie(String arg1) {
        String script = "function getAcwCookie(arg1){var _0x5e8b26=\"3000176000856006061501533003690027800375\";String[\"prototype\"][\"hexXor\"]=function(_0x4e08d8){var _0x5a5d3b=\"\";for(var _0xe89588=0;_0xe89588<this[\"length\"]&&_0xe89588<_0x4e08d8[\"length\"];_0xe89588+=2){var _0x401af1=parseInt(this[\"slice\"](_0xe89588,_0xe89588+2),16);var _0x105f59=parseInt(_0x4e08d8[\"slice\"](_0xe89588,_0xe89588+2),16);var _0x189e2c=(_0x401af1^_0x105f59)[\"toString\"](16);if(_0x189e2c[\"length\"]==1){_0x189e2c=\"0\"+_0x189e2c}_0x5a5d3b+=_0x189e2c}return _0x5a5d3b};String[\"prototype\"][\"unsbox\"]=function(){var _0x4b082b=[15,35,29,24,33,16,1,38,10,9,19,31,40,27,22,23,25,13,6,11,39,18,20,8,14,21,32,26,2,30,7,4,17,5,3,28,34,37,12,36];var _0x4da0dc=[];var _0x12605e=\"\";for(var _0x20a7bf=0;_0x20a7bf<this[\"length\"];_0x20a7bf++){var _0x385ee3=this[_0x20a7bf];for(var _0x217721=0;_0x217721<_0x4b082b[\"length\"];_0x217721++){if(_0x4b082b[_0x217721]==_0x20a7bf+1){_0x4da0dc[_0x217721]=_0x385ee3}}}_0x12605e=_0x4da0dc[\"join\"](\"\");return _0x12605e};var _0x23a392=arg1[\"unsbox\"]();arg2=_0x23a392[\"hexXor\"](_0x5e8b26);return arg2}";

        try {


            JavaScriptEngine engine = ScriptUtil.getJavaScriptEngine();
            engine.eval(script);

            String realStr = (String) engine.invokeFunction("getAcwCookie", arg1);

            return realStr;
        } catch (Exception e) {
            throw new RuntimeException("执行js出现异常");
        }
    }


}

