package com.mask.rabbit.workqueue;

import com.mask.rabbit.util.RabbitMQUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.util.Scanner;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/2/7 16:52
 * @Description: 生产者
 */
public class WorkProducer {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        // 1. 获取信道
        Channel channel = RabbitMQUtils.getChannel();
        // 2. 生成队列
        /**
         * String queue：队列名称
         * boolean durable：是否持久化，true:是，持久化到磁盘  false:否，默认是在内存中
         * boolean exclusive：该队列是否只供一个消费者消费，是否进行共享(必须同一个channel才可以消费到)。 true：是 false： 否
         * boolean autoDelete：是否自动删除。最后一个消费者断开以后，该队列是否自动删除 true:自动删除
         * Map<String, Object> arguments： 其他的一些参数，例如延时、死信队列等，这里不涉及
         */
        channel.queueDeclare(QUEUE_NAME,false,false,true,null);

        // 3. 发送消息
        Scanner scanner = new Scanner(System.in);
        while (true){
            String next = scanner.next();
            /**
             *  String exchange: 交换机。本案例不涉及交换机，使用默认的交换机
             *  String routingKey: 路由key，也就是队列名称
             *  BasicProperties props:一些参数信息 这里不涉及
             *  byte[] body:消息内容
             */
            channel.basicPublish("",QUEUE_NAME,null,next.getBytes());
            System.out.println("消息发送完毕");
        }
    }
}
