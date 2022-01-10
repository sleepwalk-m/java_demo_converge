package com.mask.io.outputstream;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author: Mask.m
 * @create: 2022/01/09 12:16
 * @description: 对象操作流 序列化
 */
public class ObjectInputStream_ {
    public static void main(String[] args) throws Exception {
        String path = "D:\\workspace\\work_demo\\java_advance\\src\\main\\java\\com\\mask\\io\\outputstream\\对象操作流写出的文件.dat";

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));

        // 注意必须按照写入的顺序读
        System.out.println(objectInputStream.read());
        System.out.println(objectInputStream.readBoolean());
        System.out.println(objectInputStream.readChar());
        System.out.println(objectInputStream.readFloat());
        System.out.println(objectInputStream.readUTF());

        // 这里可能出现serializableUID不一致的情况 建议在类中写死
        Object o = objectInputStream.readObject();
        System.out.println(((Dog)o).getName());

        objectInputStream.close();

    }
}
