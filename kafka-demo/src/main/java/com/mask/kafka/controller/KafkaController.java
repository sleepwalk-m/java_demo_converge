package com.mask.kafka.controller;

import com.alibaba.fastjson.JSON;
import com.mask.kafka.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mask.m
 * @create: 2021/07/28 23:27
 * @description: 控制器
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/send")
    public String sendMessage(){

        User user = new User("zhangsan", 18);

        // 传递普通字符串
        kafkaTemplate.send("hello-kafka","spring boot kafka ~~");

        // 发送对象 转为json字符串发送
        kafkaTemplate.send("hello-kafka", JSON.toJSONString(user));
        return "ok";
    }
}
