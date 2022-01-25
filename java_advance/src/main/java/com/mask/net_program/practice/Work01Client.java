package com.mask.net_program.practice;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/25 16:33
 * @Description: 第一题 客户端
 */
public class Work01Client {
    public static void main(String[] args) throws Exception{

        Socket socket = new Socket(InetAddress.getLocalHost(),9999);

        Scanner scanner = new Scanner(System.in);
        while (true){
            String next = scanner.next();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(next);
            writer.newLine();
            writer.flush();


            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String read = reader.readLine();
            System.out.println("服务端回复：" + read);

            if (StringUtils.equals(next,"quit")){
                reader.close();
                writer.close();

                socket.close();
            }
        }
    }
}
