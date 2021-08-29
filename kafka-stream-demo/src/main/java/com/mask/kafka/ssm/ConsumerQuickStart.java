package com.mask.kafka.ssm;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author: Mask.m
 * @create: 2021/07/11 18:42
 * @description: kakfa消费者 快速入门
 */
public class ConsumerQuickStart {

    private static final String TOPIC = "kafka-mask-out";

    public static void main(String[] args) {

        // 2. 配置参数
        Properties properties = new Properties();

        // 配置server
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.200.130:9092");
        // 配置反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");

        /*
        配置分组:
               1. 生产者发送消息，同一个组中的多个消费者只能有一个消费者接收消息
               2. 生产者发送消息，如果有多个组，每个组中只能有一个消费者接收消息,如果想要实现广播的效果，可以让每个消费者单独有一个组即可，这样每个消费者都可以接收到消息

          */
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"group2");



        // 1. 创建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // 3. 订阅主题 传参list 这里转换一下
        consumer.subscribe(Collections.singletonList(TOPIC));

        // 4. 获取消息
        // 需要不断的去监听
        while (true) {
            // Duration.ofMillis(1000) 这里要用这个传参，但是我这里环境问题切换不到1.8 所以先用过时方法先运行程序
            //ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.key());
                System.out.println(consumerRecord.value());
            }
        }


    }
}
