package com.mask.puppeteer.controller;

import com.mask.puppeteer.compoent.JdSpider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mask.m
 * @create: 2021/07/14 21:00
 * @description:
 */
@RestController
@RequestMapping("/")
public class JobController {

    @Autowired
    private JdSpider jdSpider;

    @GetMapping("/crawler")
    public String doCrawler(){
        jdSpider.crawler();
        return "OK";
    }
}