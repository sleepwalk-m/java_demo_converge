package com.mask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Mask.m
 * @version 1.0
 * @date 2021/10/13 10:25
 * @Description:
 */
@RestController
@RequestMapping("/redisTest")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public String testRedis() {
        //设置值到redis
        redisTemplate.opsForValue().set("name","lucy");
        //从redis获取值
        String name = (String)redisTemplate.opsForValue().get("name");
        return name;
    }


    @GetMapping("testLock")
    public void testLock(){
        String uuid = UUID.randomUUID().toString();
        //1获取锁，setne
        //Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid);
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", uuid,10, TimeUnit.SECONDS);
        //2获取锁成功、查询num的值
        if(lock){
            Object value = redisTemplate.opsForValue().get("num");
            //2.1判断num为空return
            if(StringUtils.isEmpty(value)){
                return;
            }
            //2.2有值就转成成int
            int num = Integer.parseInt(value+"");
            //2.3把redis的num加1
            redisTemplate.opsForValue().set("num", ++num);
            //2.4释放锁，del
            if (uuid.equals(redisTemplate.opsForValue().get("lock"))) {
                redisTemplate.delete("lock");
            }

        }else{
            //3获取锁失败、每隔0.1秒再获取
            try {
                Thread.sleep(100);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @GetMapping("testLockLua")
    public void testLockLua() {
        //1 声明一个uuid ,将做为一个value 放入我们的key所对应的值中
        String uuid = UUID.randomUUID().toString();
        //2 定义一个锁：lua 脚本可以使用同一把锁，来实现删除！
        String skuId = "25"; // 访问skuId 为25号的商品 100008348542
        String locKey = "lock:" + skuId; // 锁住的是每个商品的数据

        // 3 获取锁
        Boolean lock = redisTemplate.opsForValue().setIfAbsent(locKey, uuid, 3, TimeUnit.SECONDS);

        // 第一种： lock 与过期时间中间不写任何的代码。
        // redisTemplate.expire("lock",10, TimeUnit.SECONDS);//设置过期时间
        // 如果true
        if (lock) {
            // 执行的业务逻辑开始
            // 获取缓存中的num 数据
            Object value = redisTemplate.opsForValue().get("num");
            // 如果是空直接返回
            if (StringUtils.isEmpty(value)) {
                return;
            }
            // 不是空 如果说在这出现了异常！ 那么delete 就删除失败！ 也就是说锁永远存在！
            int num = Integer.parseInt(value + "");
            // 使num 每次+1 放入缓存
            redisTemplate.opsForValue().set("num", String.valueOf(++num));
            /*使用lua脚本来锁*/
            // 定义lua 脚本
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            // 使用redis执行lua执行
            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
            redisScript.setScriptText(script);
            // 设置一下返回值类型 为Long
            // 因为删除判断的时候，返回的0,给其封装为数据类型。如果不封装那么默认返回String 类型，
            // 那么返回字符串与0 会有发生错误。
            redisScript.setResultType(Long.class);
            // 第一个要是script 脚本 ，第二个需要判断的key，第三个就是key所对应的值。
            redisTemplate.execute(redisScript, Arrays.asList(locKey), uuid);
        } else {
            // 其他线程等待
            try {
                // 睡眠
                Thread.sleep(1000);
                // 睡醒了之后，调用方法。
                testLockLua();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
