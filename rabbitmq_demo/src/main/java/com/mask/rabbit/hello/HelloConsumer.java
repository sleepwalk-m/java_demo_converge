package com.mask.rabbit.hello;

import com.rabbitmq.client.*;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/2/7 15:21
 * @Description: hello world 消费者
 */
public class HelloConsumer {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args)  throws Exception {
        // 1. 获取连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置host和密码
        factory.setHost("");
        factory.setUsername("");
        factory.setPassword("");
        factory.setVirtualHost("my_vhost");// 由于在安装的时候设置了默认vhost 这里必须指定才能发送消息成功

        // 2. 建议连接
        Connection connection = factory.newConnection();
        // 3. 获取信道
        Channel channel = connection.createChannel();
        System.out.println("等待接收消息");

        // 推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("consumerTag = " + consumerTag);
            System.out.println("message = " + new String(message.getBody()));
        };

        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息消费被中断：consumerTag" + consumerTag);
        };




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
