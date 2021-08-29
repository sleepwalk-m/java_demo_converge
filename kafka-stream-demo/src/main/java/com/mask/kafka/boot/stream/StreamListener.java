package com.mask.kafka.boot.stream;

import com.mask.kafka.boot.config.KafkaStreamListener;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Component
public class StreamListener implements KafkaStreamListener<KStream<String, String>> {
    @Override
    public String listenerTopic() {
        return "kafka-mask-in";
    }

    @Override
    public String sendTopic() {
        return "kafka-mask-out";
    }

    @Override
    public KStream<String, String> getService(KStream<String, String> stream) {
        // 计算
       return stream.flatMapValues(new ValueMapper<String, Iterable<String>>() {
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
                });

    }


}