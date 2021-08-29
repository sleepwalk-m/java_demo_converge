package com.mask.ssm.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author: Mask.m
 * @create: 2021/07/11 18:19
 * @description: kafka生产者快速入门
 */
public class ProducerQuickStart {

    private static final String TOPIC = "kafka-mask";

    public static void main(String[] args) {



        // 2. 定义配置 broker信息 broker可以认为是kafka的一台服务器的实例
        Properties properties = new Properties();
        /*
            所需要的参数可以参考：org.apache.kafka.clients.producer.ProducerConfig

         */
        // 配置服务器地址
        properties.put("bootstrap.servers","192.168.200.130:9092");
        /*
         配置key和value的序列化 这里用kafka自带的序列化器
            参见：org.apache.kafka.common.serialization包下的：
                        StringSerializer 和 StringDeserializer
          */
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        // 配置重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG,10);


        // 1. 创建kafka生产者对象，需要构造传参配置
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);


        // 3. 创建发送的消息 这里三个参数： 1. TOPIC 2. 消息key 3. 消息value
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(TOPIC,"00001","hello kafka !");

        try {
            // 4. 发送消息
            producer.send(producerRecord);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 5. 关闭发送通道 必须关闭
        producer.close();
    }
}
