package com.mask.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author: Mask.m
 * @create: 2021/07/28 23:35
 * @description: 监听器 接收消息
 */
@Component
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(topics = {"hello-kafka"})
    public void receiveMessage(ConsumerRecord<?,?> record){

        Optional<? extends ConsumerRecord<?, ?>> optional = Optional.ofNullable(record);
        if (optional.isPresent()){
            // 从record取数据
            Object value = record.value();
            System.out.println("value = " + value);
        }
    }

}
