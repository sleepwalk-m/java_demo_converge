package com.mask.kafka.boot.config;

/**
 * 流数据的监听消费者实现的接口类，系统自动会通过
 * KafkaStreamListenerFactory类扫描项目中实现该接口的类
 * 并注册为流数据的消费端
 *
 * 其中泛型可是KStream或KTable
 * @param <T>
 */
public interface KafkaStreamListener<T> {

    // 监听的类型
    String listenerTopic();
    // 处理结果发送的类
    String sendTopic();
    // 对象处理逻辑
    T getService(T stream);

}
