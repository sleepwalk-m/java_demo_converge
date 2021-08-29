package com.mask.kafka.ssm;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.util.Arrays;
import java.util.Properties;

/**
 * @author: Mask.m
 * @create: 2021/08/29 17:40
 * @description: kafkastream案例，统计多次消息中的单词的个数
 */
public class KafkaStreamSample {

    public static void main(String[] args) {
        // kafka配置信息
        Properties properties = new Properties();
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.200.130:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());// 序列化
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());// 反序列化
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-faststart");

        /// 构建stream
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        // 流式计算-自定义 抽取的方法
        group(streamsBuilder);

        // 创建stream对象
        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);

        // 开启stream流计算
        kafkaStreams.start();
    }

    /**
     * 流式计算 抽取的方法
     *
     * @param builder
     */
    private static void group(StreamsBuilder builder) {
        // 获取kstream对象
        KStream<String, String> stream = builder.stream("kafka-mask-in");//生产者的topic

        // 计算
        stream.flatMapValues(new ValueMapper<String, Iterable<String>>() {
            /**
             * 把消息中的词组转换为单词 放入集合
             * @param value
             * @return
             */
            @Override
            public Iterable<String> apply(String value) {
                // 这里接收到消息： kafka steam
                System.out.println("消息的value:" + value);
                String[] s = value.split(" ");
                return Arrays.asList(s); // 返回集合
            }
        }).map(new KeyValueMapper<String, String, KeyValue<String, String>>() {
            /**
             * 对消息的key value重新赋值，因为producer发消息过来的key=00001，跟我们流式计算统计单词数量没关系 所以这一步修改一下消息的kv值，现在key就是单词了
             * @param key
             * @param value
             * @return
             */
            @Override
            public KeyValue<String, String> apply(String key, String value) {
                return new KeyValue<>(value, value);
            }
        }).groupByKey() // 根据key值聚合
                // 时间聚合窗口
                .windowedBy(TimeWindows.of(5000)) // 5s聚合一次 相当于每5秒统计一次 发送
                // 消息的value就是聚合后的单词数统计 long类型; 注意上面根据key聚合了，那么再去count一下就是求数量
                .count(Materialized.as("count-word-num-0001")) // 不重复即可
                .toStream() // 转为KStream
                // 把处理后的key和value转成string 这里的key和value就已经是单词:个数了
                .map((key, value) -> {
                    return new KeyValue<>(key.key().toString(), value.toString());
                })
                // 发送消息
                .to("kafka-mask-out"); // 消费者的topic

    }


}
