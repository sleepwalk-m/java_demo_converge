package com.mask.net_program.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/25 16:07
 * @Description: UDP 发送端
 */
public class UDPSendB {
    public static void main(String[] args) throws Exception{
        // 1. 创建DatagramSocket 对象 向9999端口
        DatagramSocket datagramSocket = new DatagramSocket(9998);

        // 2. 包装数据
        byte[] data = "hello,明天一起去吃火锅怎么样？".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data,0,data.length);
        datagramPacket.setPort(9999);
        datagramPacket.setAddress(InetAddress.getLocalHost());
        // 3. 发送数据
        datagramSocket.send(datagramPacket);
        System.out.println("发送端发送完毕，开始接收接收端的回复消息~");

        byte[] rece = new byte[1024];
        datagramPacket = new DatagramPacket(rece,0,rece.length);
        datagramSocket.receive(datagramPacket);

        int length = datagramPacket.getLength();
        System.out.println(new String(datagramPacket.getData(),0,length));


        // 4. 关闭socket
        datagramSocket.close();
    }
}
