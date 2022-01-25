package com.mask.net_program.upload;

import com.mask.net_program.util.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/24 12:29
 * @Description: TCP文件上传服务端
 */
public class TCPFileUploadServer {
    public static void main(String[] args) throws Exception {
        // 1. 开启服务
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        // 2. 读取客户端发来的图片
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        // 3. 写入本地
        String filePath = "D:\\workspace\\study_code_demo-master\\java_advance\\src\\copy.jpg";
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(bytes);
        fos.close();
        System.out.println("图片保存成功");

        // 4. 发送确认消息
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("收到图片");
        writer.newLine();
        writer.flush();
        socket.shutdownOutput();
        // 5. 关流
        writer.close();
        bis.close();
        socket.close();
        serverSocket.close();
    }
}
