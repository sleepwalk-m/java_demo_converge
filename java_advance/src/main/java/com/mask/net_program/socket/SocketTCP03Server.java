package com.mask.net_program.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/24 11:46
 * @Description: tcp socket 服务端，回写至客户端，字符流完成
 */
public class SocketTCP03Server {
    public static void main(String[] args) throws IOException {
        // 1. 开启一个服务 可以创建多个socket对象
        ServerSocket serverSocket = new ServerSocket(9999);
        // 2. 等待连接,返回socket对象 程序会阻塞
        Socket socket = serverSocket.accept();

        // 3. 获取输入流
        InputStream inputStream = socket.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello,client 字符流");
        bufferedWriter.newLine();// 换行
        bufferedWriter.flush();// 必须刷新
        socket.shutdownOutput();// 输出的结束标记

        bufferedWriter.close();
        reader.close();
        socket.close();
        serverSocket.close();
    }
}
