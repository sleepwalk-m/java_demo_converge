package com.mask.net_program.obj;

import com.mask.net_program.obj.server.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Mask.m
 * @create: 2022/01/31 13:50
 * @description: 对象转换流演示
 *
 * 结论：
 *  对象转换流 必须转换同一个对象，如果对象包名不同 也是不能转换的
 */
public class ObjectStreamServer_ {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(8885);

        Socket socket = serverSocket.accept();

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        User user = (User) ois.readObject();

        System.out.println("服务端：" + user);
        ois.close();
        socket.close();
        serverSocket.close();
    }
}
