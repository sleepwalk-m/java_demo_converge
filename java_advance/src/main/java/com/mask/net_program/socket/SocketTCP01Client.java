package com.mask.net_program.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/24 11:46
 * @Description: tcp socket 客户端
 */
public class SocketTCP01Client {
    public static void main(String[] args) throws Exception {
        // 1. 连接主机 这里用的都是本机ip
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        // 2. 开启一个输出流
        OutputStream outputStream = socket.getOutputStream();
        // 3. 写数据
        outputStream.write("hello,server".getBytes());
        // 4. 关流
        outputStream.close();
        socket.close();
    }
}
