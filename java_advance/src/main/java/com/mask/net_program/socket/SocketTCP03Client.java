package com.mask.net_program.socket;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/24 11:46
 * @Description: tcp socket 客户端,读取服务端回复的消息，字符流完成
 */
public class SocketTCP03Client {
    public static void main(String[] args) throws Exception {
        // 1. 连接主机 这里用的都是本机ip
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        // 2. 开启一个输出流
        OutputStream outputStream = socket.getOutputStream();
        // 3. 写数据
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello,server 字符流");
        bufferedWriter.newLine();// 换行
        bufferedWriter.flush();// 必须刷新
        socket.shutdownOutput();// 输出的结束标记

        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }

        // 4. 关流
        reader.close();
        bufferedWriter.close();
        socket.close();
    }
}
