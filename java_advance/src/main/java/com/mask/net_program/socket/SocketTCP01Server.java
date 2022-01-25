package com.mask.net_program.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/24 11:46
 * @Description: tcp socket 服务端
 */
public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
        // 1. 开启一个服务 可以创建多个socket对象
        ServerSocket serverSocket = new ServerSocket(9999);
        // 2. 等待连接,返回socket对象 程序会阻塞
        Socket socket = serverSocket.accept();

        // 3. 获取输入流
        InputStream inputStream = socket.getInputStream();

        byte[] bytes = new byte[1024];
        int len;
        while ((len = inputStream.read(bytes)) != -1){
            System.out.println(new String(bytes,0,len));
        }

        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
