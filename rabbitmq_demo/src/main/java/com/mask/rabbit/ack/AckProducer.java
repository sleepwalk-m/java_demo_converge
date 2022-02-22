package com.mask.rabbit.ack;

import com.mask.rabbit.util.RabbitMQUtils;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/2/8 10:26
 * @Description: 演示ack机制 没有手动确认的消息会被mq重新放回队列
 */
public class AckProducer {

    public static final String QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws Exception{
        Channel channel = RabbitMQUtils.getChannel();

        /**
         * String queue：队列名称
         * boolean durable：是否持久化，true:是，持久化到磁盘  false:否，默认是在内存中
         * boolean exclusive：该队列是否只供一个消费者消费，是否进行共享(必须同一个channel才可以消费到)。 true：是 false： 否
         * boolean autoDelete：是否自动删除。最后一个消费者断开以后，该队列是否自动删除 true:自动删除
         * Map<String, Object> arguments： 其他的一些参数，例如延时、死信队列等，这里不涉及
         */
        channel.queueDeclare(QUEUE_NAME,false,false,true,null);


        // 发送消息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String msg = scanner.next();
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            System.out.println("消息发送成功");
        }
    }
}
