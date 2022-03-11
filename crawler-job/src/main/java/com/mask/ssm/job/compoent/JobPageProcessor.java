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
        List<String> urlList = json.jsonPath("engine_jds[*].job_href").all();
        for (String s : urlList) {
            Request request = new Request(s);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("level","detail"); // 标识位
            request.setExtras(map);
            setGetHeaders(request);
            page.addTargetRequest(request);
        }
    }

    public Site getSite() {
        return Site.me();
    }
    
    
    /**
     * 添加请求伪装
     *
     * @param request
     */
    public void setGetHeaders(Request request){
        request.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        request.addHeader("Accept-Encoding","gzip, deflate, br");
        request.addHeader("Accept-Language","zh-CN,zh;q=0.9");
        request.addHeader("Cache-Control","max-age=0");
        request.addHeader("Connection","keep-alive");
        request.addHeader("Host","jobs.51job.com");
        request.addHeader("Referer","https://search.51job.com/");
        request.addHeader("Upgrade-Insecure-Requests","1");
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        request.addHeader("Cookie","_uab_collina=164697298197739608741247; guid=68000cc512692910ebca2e4b88483fc3; nsearch=jobarea%3D%26%7C%26ord_field%3D%26%7C%26recentSearch0%3D%26%7C%26recentSearch1%3D%26%7C%26recentSearch2%3D%26%7C%26recentSearch3%3D%26%7C%26recentSearch4%3D%26%7C%26collapse_expansion%3D; _ujz=MTM1NDI3NTI4MA%3D%3D; ps=needv%3D0; 51job=cuid%3D135427528%26%7C%26cusername%3DkR0zqGBUwsJ21RBVsr7IrV6QnDUpssj7I9RHuRbAOHo%253D%26%7C%26cpassword%3D%26%7C%26cname%3DC4s7gOjDziCFEBwn38nIIw%253D%253D%26%7C%26cemail%3D9%252BHyMY7NfA9cKB6f4EjaMu2JKkOvVqXybItUpoY0EpU%253D%26%7C%26cemailstatus%3D3%26%7C%26cnickname%3D%26%7C%26ccry%3D.0gtvb.ae5XXM%26%7C%26cconfirmkey%3DjirwMYm783yKE%26%7C%26cautologin%3D1%26%7C%26cenglish%3D0%26%7C%26sex%3D0%26%7C%26cnamekey%3DjinFgXciPS3Q6%26%7C%26to%3D79571c03a0b0fd50d71fa00e004d4b36622aced5%26%7C%26; slife=lowbrowser%3Dnot%26%7C%26lastlogindate%3D20220311%26%7C%26securetime%3DUW0DNlIwVDZSOVNqW2IAbVtqUmw%253D; privacy=1646972647; search=jobarea%7E%60080200%7C%21ord_field%7E%600%7C%21recentSearch0%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21recentSearch1%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA32%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%B2%E2%CA%D4%B9%A4%B3%CC%CA%A6%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21; acw_tc=76b20ffb16469726557045163e08e23efa4fc92f0a4263d7f7d0a11c749644; acw_sc__v2=622aceefe4393bbf8956b584ee673b13915d7d20; ssxmod_itna=YqRxyDnDu73Dwxeq0de=4jxhNPDtrK7zTDcegQPx0HWgeGzDAxn40iDt=rwBptB7io5zQj8A4apWGOp5=6F9n0GxLCCaceDHxY=DUE7G3QTDeetD5xGoDPxDeDA7KiTDY4DdXLkV=DEDYQDYqGRDB6X+qDfAqGWzd=DmqiDGTIUD7QDIM=MS3b42eDSW0UY+B=DjTTD/feF1B=3kZa5nuUbNpIxQDDHDfyTqRwz5RhY8CpnQniDtqD9Yl=DbSdQMat/8C+xi7pUvB5b3EPP+0q4i0xIi74PeLe4zDxqzhh++Gycoh+WS5DAixPQGMeD=; ssxmod_itna2=YqRxyDnDu73Dwxeq0de=4jxhNPDtrK7zTDcegDnF3FWoDs37DLQ7iQTD+Z4nbnQGKlxn2jhVIOeO2OlQ8nI4s3KWLWt5ezx=A44rzxjvcYhaAlBdeuYLI1bzqCQKq8KvB9KY4+E1gvrZZq8D=ioq8aNAcvrdtT+6OcWUcqkr9qFWfB1/OomWcIxuioCw9AmmSqCzD8pvSw+8K8N6tB7HbMAWtKw29FLk9K=4vgQ0lffN9qjogF2nQY8KdZ05VduU3LRCR1k/UQ2z4zyPq/7T+6Xt4mgUtFMfs=M3yeI1SQilmXTSpn6EgoMEsoOo/qYAYiG+zThzA3ol3=DnY=CGDe+5ChqQ+VdwalOzQw00rFghpQmlwwvmmcMkXd=smN/w=2WajlhBqz6emt81knTLCwEDDwhxQQGxeqwW4CWDw7WmKqG0n8iDwexPeKWDTHBP4Dqu0crPxkAWbOpxBhLSxKYcuFqcSP09m5GPNiDPDGcDG7tGdm=4Y7dIrxpiDD==");
    }
    
}
