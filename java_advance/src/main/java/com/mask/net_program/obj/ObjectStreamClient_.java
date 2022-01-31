package com.mask.net_program.obj;

import com.mask.net_program.obj.client.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author: Mask.m
 * @create: 2022/01/31 13:50
 * @description: 对象转换流演示
 */
public class ObjectStreamClient_ {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(), 8885);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        User user = new User("1", "张三");
        oos.writeObject(user);
        System.out.println("客户端：" + user);
        oos.close();
        socket.close();
    }

}
