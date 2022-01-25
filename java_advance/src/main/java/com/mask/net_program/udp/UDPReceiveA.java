package com.mask.net_program.udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/25 16:07
 * @Description: UDP 接收端
 */
public class UDPReceiveA {
    public static void main(String[] args) throws Exception{
        // 1. 准备在9999端口接收数据
        DatagramSocket datagramSocket = new DatagramSocket(9999);
        // 2. 构建一个DatagramPacket对象 接收数据
        byte[] buf = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buf,buf.length);

        // 3. 调用接收方法，将数据填充到packet
        // 如果没有发送端发数据时，这里会一直等待 阻塞
        datagramSocket.receive(datagramPacket);

        // 4. 取出数据
        int length = datagramPacket.getLength();
        System.out.println("length = " + length);
        byte[] data = datagramPacket.getData();
        System.out.println(new String(data,0,length));
        System.out.println("接收端收到消息，开始回复发送端");

        byte[] rece = "好的，明天见".getBytes();
        datagramPacket = new DatagramPacket(rece,0,rece.length);
        datagramPacket.setAddress(InetAddress.getLocalHost());
        datagramPacket.setPort(9998);
        datagramSocket.send(datagramPacket);
        System.out.println("接收端消息发送完毕~");

        // 5. 关闭socket
        datagramSocket.close();
    }
}
