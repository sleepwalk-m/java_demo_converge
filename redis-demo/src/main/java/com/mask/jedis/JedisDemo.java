package com.mask.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Mask.m
 * @version 1.0
 * @date 2021/9/27 15:42
 * @Description: jedis连接redis
 *
 */
public class JedisDemo {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("47.98.218.161", 6379);
        jedis.auth("toortoor123");

        String mask = jedis.get("mask");
        System.out.println("mask = " + mask);


//        Map<String, String> map = jedis.hgetAll("dog");
//        System.out.println("map = " + map);
//
//        List<String> list = jedis.lrange("list", 0, 5);
//        System.out.println("list = " + list);
//
//        Set<String> set = jedis.smembers("set");
//        System.out.println("set = " + set);
//
//
//        Set<Tuple> zset = jedis.zrangeWithScores("zset", 0, 10);
//        System.out.println("zset = " + zset);


    }
}
