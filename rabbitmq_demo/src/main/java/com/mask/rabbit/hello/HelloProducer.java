package com.mask.rabbit.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/2/7 15:10
 * @Description: hello world 生产者
 */
public class HelloProducer {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args)  throws Exception{
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

        // 5. 生成队列
        /**
         * String queue：队列名称
         * boolean durable：是否持久化，true:是，持久化到磁盘  false:否，默认是在内存中
         * boolean exclusive：该队列是否只供一个消费者消费，是否进行共享(必须同一个channel才可以消费到)。 true：是 false： 否
         * boolean autoDelete：是否自动删除。最后一个消费者断开以后，该队列是否自动删除 true:自动删除
         * Map<String, Object> arguments： 其他的一些参数，例如延时、死信队列等，这里不涉及
         */
        channel.queueDeclare(QUEUE_NAME,false,false,true,null);

        // 6. 发送消息
        String msg = "hello world";

        /**
         *  String exchange: 交换机。本案例不涉及交换机，使用默认的交换机
         *  String routingKey: 路由key，也就是队列名称
         *  BasicProperties props:一些参数信息 这里不涉及
         *  byte[] body:消息内容
         */
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        System.out.println("消息发送完毕");
    }
}
