package com.mask.crawler.controller;

import com.mask.crawler.compoent.Crawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mask.m
 * @create: 2021/07/10 14:50
 * @description:
 */
@RestController
@RequestMapping("/")
public class ItemController {

    @Autowired
    private Crawler crawler;

    @GetMapping("/crawler")
    public String doCrawler(String action){
        // 用了一个新的线程来跑
        if ("start".equals(action)){
            new Thread(new Runnable() {
                public void run() {
                    crawler.doCrawler();
                }
            }).start();
        }

        return "ok";
    }

}
