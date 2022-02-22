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
        request.addHeader("Cookie","_uab_collina=164493144361047106814312; guid=68000cc512692910ebca2e4b88483fc3; nsearch=jobarea%3D%26%7C%26ord_field%3D%26%7C%26recentSearch0%3D%26%7C%26recentSearch1%3D%26%7C%26recentSearch2%3D%26%7C%26recentSearch3%3D%26%7C%26recentSearch4%3D%26%7C%26collapse_expansion%3D; search=jobarea%7E%60080200%7C%21ord_field%7E%600%7C%21recentSearch0%7E%60080200%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA32%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA%A1%FB%A1%FA0%A1%FB%A1%FA%B2%E2%CA%D4%B9%A4%B3%CC%CA%A6%A1%FB%A1%FA2%A1%FB%A1%FA1%7C%21; acw_tc=2f624a3716449416785167203e394e76da51d769899fc9b28c9534553fb33c; acw_sc__v2=620bd16e34444af2dff2b0f4e784c55e0d37f87c; ssxmod_itna=eq0x9D07GQ6rDXDnQGqwW4Rh8DyAxWqre0QzbFDl=e4xA5D8D6DQeGTiubeYidYFe4xxNfKFRbAbKW1i4Lp=ZniP4LVGDB3DEx06bdj7D4GGUxBYDQxAYDGDDpkDj4ibDYfzODjBItzZCq=D3qDwDB=DmqG2Kn=Dm4DfDDdyD3xyUwLe5CrDALmMK7DyD0tDIqGX4oaeCqDBWpaeSMUi51IPlDDHF7G15i+1qxP=DYPaiM0qxBQD7u2Sl821wMXl3uBsG8hWlOhY4hpb3Gqe73pbnYB+zDh3tEAxnZlYf0vMxDfxz54s3rDD; ssxmod_itna2=eq0x9D07GQ6rDXDnQGqwW4Rh8DyAxWqre0QzbD6pKfgx0yG9D03ednWPXD6GuHLWX8DuZ4x=sneFjYu8YO/LnRhoqzl8apt6FChy8hRfeQHj=2+jdHC4HwI7C8roMtQ=00ybl/gfMZFbBYxzNEpWqI5OcW7GK9uNB7KWD+AH/ZTwFaHTW17rPfW1NIKsH83pn13kR2SCmfpf1FoAkUvf6Uf6vbMU7U7r/RIIZUPtYE5fPs7Rr+86NLkvYkjGjrcHC8l7I9jpT5jLql6wiXS7MO09EstbmwvAa8zB6HmZk3Q=0AVc+p4DKqxXDDLxD2KY4324erQK0DmGDD==");
    }
    
}
