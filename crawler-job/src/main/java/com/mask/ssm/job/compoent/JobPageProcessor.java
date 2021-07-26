package com.mask.ssm.job.compoent;

import com.mask.ssm.job.pojo.JobInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2021/07/14 20:15
 * @description:
 */
@Component
public class JobPageProcessor implements PageProcessor {



    public void process(Page page) {
        // 上下文 可以传递一些数据
        Map<String, Object> extras = page.getRequest().getExtras();
        String level = extras.get("level").toString();

  /*      if (StringUtils.isBlank(level)){
            level = "list";
        }*/

        switch (level){
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
        if (StringUtils.isNotBlank(tempJobStr)){
            String[] split = tempJobStr.split("|");
            if (split.length > 4){
                jobAddr = split[0];
                time = split[split.length-1];
            }
        }
        String jobInfoStr = html.$("div.bmsg.job_msg.inbox p").xpath("///allText()").get();
        String tempSalaryStr = html.$("div.cn strong").xpath("///allText()").get();
        String salaryMin = "";
        String salaryMax = "";
        if (StringUtils.isNotBlank(tempSalaryStr)){
            String[] salarySplit = tempSalaryStr.split("万")[0].split("-");
            if (salarySplit.length > 1){
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
        page.putField("jobInfo",jobInfo);
    }

    /**
     * 解析列表页
     *
     * @param page
     */
    private void parseList(Page page) {
        Json json = page.getJson();
        // 获取所有的目标详情url
        List<String> urlList = json.jsonPath("engine_search_result[*].job_href").all();
        for (String s : urlList) {
            Request request = new Request(s);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("level","detail"); // 标识位
            request.setExtras(map);
            page.addTargetRequest(request);
        }
    }

    public Site getSite() {
        return Site.me();
    }
}
