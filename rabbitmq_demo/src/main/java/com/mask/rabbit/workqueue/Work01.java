package com.mask.rabbit.workqueue;

import com.mask.rabbit.util.RabbitMQUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/2/7 16:49
 * @Description: 工作线程 （也就是消费者）
 */
public class Work01 {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();

        // 推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("consumerTag = " + consumerTag);
            System.out.println("message = " + new String(message.getBody()));
        };

        // 消息消费取消之后的回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息消费被中断：consumerTag" + consumerTag);
        };

        System.out.println("work03 接收消息中");

        // 4. 接收消息
        /**
         * String queue: 队列名称
         * boolean autoAck: 是否自动确认消费消息成功 true:自动确认 false：手动确认
         * DeliverCallback deliverCallback: 消费者消费未成功的回调
         * CancelCallback cancelCallback: 消费者消息成功后的回调
         */
        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);

    }
}
