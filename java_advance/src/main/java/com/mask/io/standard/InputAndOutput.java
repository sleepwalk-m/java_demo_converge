package com.mask.io.standard;

import java.util.Scanner;

/**
 * @author: Mask.m
 * @create: 2022/01/09 12:40
 * @description: 标准输入 输出流
 */
public class InputAndOutput {

    public static void main(String[] args) {
        // 1. public final static InputStream in = null;
        // 2. System.in 编译类型 InputStream
        // 3. System.in 运行类型 BufferedInputStream
        // 4. 代表标准输入流 -> 键盘
        System.out.println(System.in.getClass());
        // scanner 用的就是system.in
        Scanner scanner = new Scanner(System.in);
        System.out.println("开始输入");
        String next = scanner.next();
        System.out.println("next = " + next);


        // 1. public final static PrintStream out = null;
        // 2. System.out 编译类型 PrintStream
        // 3. System.out 运行类型 PrintStream
        // 4. 代表标准输出流 -> 显示器
        System.out.println("你好，北京");
    }
}
