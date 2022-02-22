package com.mask;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mask.pojo.Resource;
import com.mask.service.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mask.m
 * @version 1.0
 * @date 2021/9/27 15:53
 * @Description:
 */
@SpringBootTest(classes = RedisApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {

    //@Autowired
    //StringRedisTemplate redisTemplate;

    @Autowired
    RedisTemplate redisTemplate;


    @Test
    public void test01(){


        //Object o = redisTemplate.opsForHash().get("dog", "age");
        //System.out.println("o = " + o);

//        redisTemplate.opsForHash().put("cat","name","flower");
//        Object o = redisTemplate.opsForHash().get("cat", "name");
//        System.out.println("o = " + o);

        //redisTemplate.opsForHash().put("animal","种类","狮子");
        Object o = redisTemplate.opsForHash().get("animal", "种类");
        System.out.println("o = " + o);

        //redisTemplate.opsForHash()

    }

    @Autowired
    private ResourceService resourceService;

    @Test
    public void test02(){
        List<Long> ids = new ArrayList<>();
        ids.add(603982542332235201L);
        ids.add(603983082961243905L);
        List<Resource> list = resourceService.list(Wrappers.<Resource>lambdaQuery().in(Resource::getMenuId, ids));
        System.out.println("list = " + list);


    }


}
