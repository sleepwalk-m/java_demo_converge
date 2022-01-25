package com.mask.net_program.socket;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/24 11:46
 * @Description: tcp socket 客户端,读取服务端回复的消息
 */
public class SocketTCP02Client {
    public static void main(String[] args) throws Exception {
        // 1. 连接主机 这里用的都是本机ip
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        // 2. 开启一个输出流
        OutputStream outputStream = socket.getOutputStream();
        // 3. 写数据
        outputStream.write("hello,server".getBytes());
        socket.shutdownOutput();// 输出的结束标记

        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1){
            System.out.println(new String(bytes,0,len));
        }

        // 4. 关流
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
