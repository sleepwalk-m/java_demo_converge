package com.mask.net_program.upload;

import cn.hutool.core.util.StrUtil;
import com.mask.net_program.util.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Mask.m
 * @version v1.0
 * @date 2022/1/24 12:30
 * @Description: TCP文件上传 客户端
 */
public class TCPFileUploadClient {
    public static void main(String[] args) throws Exception{
        // 1. 开启socket
        Socket socket = new Socket(InetAddress.getLocalHost(),8888);

        // 2. 讲图片读取成byte[]
        String filePath = "C:\\Users\\wb-jf930343\\Desktop\\material\\4c19a77b-b36d-4372-8631-66b9559b48b3.jpg";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
        byte[] bytes = StreamUtils.streamToByteArray(bis);
        // 3. 利用socket写出
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);
        bis.close();
        socket.shutdownOutput();
        // 4. 读取服务端返回的消息
        String msg = StreamUtils.streamToString(socket.getInputStream());
        if (StrUtil.equals(msg,"收到图片")){
            System.out.println("客户端退出");
            bos.close();
            socket.close();
        }
    }
}
