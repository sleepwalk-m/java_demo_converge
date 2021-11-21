package com.mask.mongo.config;

import com.mongodb.MongoClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Data
@Configuration
@PropertySource("classpath:mongo.properties")
@ConfigurationProperties(prefix="mongo")
public class MongoDBconfigure {

    private String host;
    private Integer port;
    private String dbname;

    /**
     * 自己构建MongoTemplate
     * @return
     */
    @Bean
    public MongoTemplate getMongoTemplate() {
        return new MongoTemplate(getSimpleMongoDbFactory());
    }

    /**
     * 如果有用户名和密码也可以通过MongoClient其他构造函数传参
     * @return
     */
    public SimpleMongoDbFactory getSimpleMongoDbFactory() {
        return new SimpleMongoDbFactory(new MongoClient(host, port), dbname);
    }

}