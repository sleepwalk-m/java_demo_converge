package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mask.m
 * @create: 2021/01/19 22:29
 * @description:
 */
@RestController
@RequestMapping("/bank")
public class JsonController {

    @GetMapping("/cost.json")
    public void transfer(String title){
        if (title != null){

        }

        System.out.println(title);

    }

}
