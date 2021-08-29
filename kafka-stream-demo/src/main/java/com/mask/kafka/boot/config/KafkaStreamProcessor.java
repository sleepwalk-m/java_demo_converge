package com.mask.kafka.boot.config;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * KafkaStream自动处理包装类
 */
public class KafkaStreamProcessor {

    // 流构建器
    StreamsBuilder streamsBuilder;
    private String type;
    KafkaStreamListener listener;

    public KafkaStreamProcessor(StreamsBuilder streamsBuilder,KafkaStreamListener kafkaStreamListener){
        this.streamsBuilder = streamsBuilder;
        this.listener = kafkaStreamListener;
        this.parseType();
        Assert.notNull(this.type,"Kafka Stream 监听器只支持kstream、ktable,当前类型是"+this.type);
    }

    /**
     * 通过泛型类型自动注册对应类型的流处理器对象
     * 支持KStream、KTable
     * @return
     */
    public Object doAction(){
        if("kstream".equals(this.type)) {
            KStream<?, ?> stream = streamsBuilder.stream(listener.listenerTopic(), Consumed.with(Topology.AutoOffsetReset.LATEST));
            stream=(KStream)listener.getService(stream);
            stream.to(listener.sendTopic());
            return stream;
        }else{
            KTable<?, ?> table = streamsBuilder.table(listener.listenerTopic(), Consumed.with(Topology.AutoOffsetReset.LATEST));
            table = (KTable)listener.getService(table);
            table.toStream().to(listener.sendTopic());
            return table;
        }
    }

    /**
     * 解析传入listener类的泛型类
     */
    private void parseType(){
        Type[] types = listener.getClass().getGenericInterfaces();
        if(types!=null){
            for (int i = 0; i < types.length; i++) {
                if( types[i] instanceof ParameterizedType){
                    ParameterizedType t = (ParameterizedType)types[i];
                    String name = t.getActualTypeArguments()[0].getTypeName().toLowerCase();
                    if(name.contains("org.apache.kafka.streams.kstream.kstream")||name.contains("org.apache.kafka.streams.kstream.ktable")){
                        this.type = name.substring(0,name.indexOf('<')).replace("org.apache.kafka.streams.kstream.","").trim();
                        break;
                    }
                }
            }
        }
    }

}
