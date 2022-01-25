package com.mask.net_program;

import cn.hutool.json.JSONUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: Mask.m
 * @create: 2022/01/19 21:47
 * @description: InetAddress的API演示
 */
public class API_ {
    public static void main(String[] args) throws UnknownHostException {

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localHost = " + localHost);// 本机的主机名/ip地址 SleepWalk/192.168.200.1

        // 2. 根据主机名获取InetAddress对象
        InetAddress sleepWalk = InetAddress.getByName("SleepWalk");
        System.out.println("sleepWalk = " + sleepWalk);//SleepWalk/192.168.200.1

        // 3. 根据域名获取InetAddress对象
        InetAddress baidu = InetAddress.getByName("www.baidu.com");
        System.out.println("baidu = " + baidu);//www.baidu.com/180.101.49.11
        String hostAddress = baidu.getHostAddress();
        System.out.println("hostAddress = " + hostAddress);// 180.101.49.11

        // 4. 百度有很多服务器 可以拿到很多ip
        InetAddress[] baidus = InetAddress.getAllByName("www.baidu.com");
        System.out.println("baidus = " + JSONUtil.toJsonStr(baidus));

        // 5. 通过InetAddress对象 获取主机或者域名
        String hostName = baidu.getHostName();
        String hostName1 = sleepWalk.getHostName();
        System.out.println("hostName1 = " + hostName1);// sleepwalk
        System.out.println("hostName = " + hostName);// www.baidu.com
    }
}
