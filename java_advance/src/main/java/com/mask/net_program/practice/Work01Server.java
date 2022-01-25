package com.mask.net_program.practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/25 16:32
 * @Description: 第一题 服务端
 */
public class Work01Server {
    public static void main(String[] args) throws Exception{

        ServerSocket serverSocket = new ServerSocket(9999);
        boolean flag = false;
        while (true){
            Socket socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = reader.readLine();
            String receive = "";
            switch (line){
                case "name":
                    receive = "我是navo";
                    break;
                case "hobby":
                    receive = "编写Java程序";
                    break;
                case "quit":
                    flag = true;
                    break;
                default:
                    receive = "你说啥呢";
                    break;
            }

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(receive);
            writer.newLine();
            writer.flush();

            if (flag) {
                writer.close();
                reader.close();
                socket.close();
                serverSocket.close();
                break;
            }
        }
    }
}
