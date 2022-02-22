package com.mask.rabbit.ack;

import com.mask.rabbit.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/2/8 10:30
 * @Description: 演示ack 手动确认后 消费者宕机，则消息会重新入队，交由其他消费者消费
 */
public class Worker01 {

    public static final String QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();


        DeliverCallback deliverCallback = (consumerTag, message) -> {
            try {
                // 睡1s 模拟业务逻辑需要走1s
                Thread.sleep(1000);
                System.out.println("W1消费了消息：" + new String(message.getBody(), StandardCharsets.UTF_8));
                channel.basicAck(message.getEnvelope().getDeliveryTag(),false);// tag，是否批量应答 具体解释见笔记
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        channel.basicConsume(QUEUE_NAME,false,deliverCallback,consumerTag -> {
            System.out.println("消费消息取消。。。");
        });
    }
}
