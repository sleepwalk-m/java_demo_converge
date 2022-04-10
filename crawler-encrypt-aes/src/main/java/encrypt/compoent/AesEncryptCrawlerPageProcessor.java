package encrypt.compoent;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2022/04/10 13:00
 * @description:
 */
public class AesEncryptCrawlerPageProcessor implements PageProcessor {


    @Override
    public void process(Page page) {
        Map<String, Object> extras = page.getRequest().getExtras();
        String level = extras.get("pageLevel").toString();

        // AES CBC解密数据

        String key = "jo8j9wGw%6HbxfFn";
        String iv = "0123456789ABCDEF";
        AES aes = new AES(Mode.CBC,Padding.PKCS5Padding,key.getBytes(),iv.getBytes());

        String result = aes.decryptStr(page.getRawText());
        System.out.println("result = " + result);


    }



    @Override
    public Site getSite() {
        return Site.me().setTimeOut(30000);
    }

}
