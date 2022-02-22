package com.mask.rabbit.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/2/7 16:49
 * @Description:
 */
public class RabbitMQUtils {

    public static Channel getChannel() throws Exception{
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
        return connection.createChannel();
    }
}
