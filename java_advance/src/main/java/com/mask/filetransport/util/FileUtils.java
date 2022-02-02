package com.mask.filetransport.util;
 
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
 
public class FileUtils {
    /**
     * 支持断点续传
     * @src 拷贝的原文件
     * @desc 拷贝的位置
     * @threadNum 开启的线程数
     */
    public static void transportFile(File src, File desc, int threadNum) throws Exception {
        // 每一个线程读取的大小
        int part = (int)Math.ceil(src.length() / threadNum);
        // 存储多个线程、用于阻塞主线程
        List<Thread> list = new ArrayList<>();
 
        // 定义一个基于多线程 的 hashmap
        final Map<Integer, Integer> map = new ConcurrentHashMap<>();
        // 读取 日志文件中的数据
        String[] $data = null ;
 
        String logName = desc.getCanonicalPath() + ".log";
 
        File fl = new File(logName);
 
        if (fl.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(fl));
            String data = reader.readLine();
            // 拆分 字符串
            $data = data.split(",");
            reader.close();
        }
 
        final String[] _data = $data ;
 
        for (int i = 0; i < threadNum; i++) {
            final int k = i ;
            Thread thread = new Thread(() -> {
                // 线程具体要做的事情
                RandomAccessFile log = null ;
                try {
                    RandomAccessFile in = new RandomAccessFile(src, "r");
                    RandomAccessFile out = new RandomAccessFile(desc, "rw");
 
                    log = new RandomAccessFile(logName, "rw");
                    // 从指定位置读
                    in.seek(_data ==null ?k * part : Integer.parseInt(_data[k]) );
                    out.seek(_data ==null ?k * part : Integer.parseInt(_data[k]) );
 
                    byte[] bytes = new byte[1024 * 2];
                    int len = -1, plen = 0;
 
                    while (true) {
                        len = in.read(bytes);
 
                        if (len == -1) {
                            break;
                        }
                        // 如果不等于 -1 ， 则 累加求和
                        plen += len;
 
                        // 将读取的字节数，放入 到 map 中
                        map.put(k,  plen + (_data ==null ?k * part : Integer.parseInt(_data[k])) );
 
                        // 将读取到的数据、进行写入
                        out.write(bytes, 0, len);
                        // 将 map 中的数据进行写入文件中
                        log.seek(0); // 直接覆盖全部文件
                        StringJoiner joiner = new StringJoiner(",");
                        map.forEach((key, val)-> joiner.add(String.valueOf(val)));
                        log.write(joiner.toString().getBytes(StandardCharsets.UTF_8));
 
                        if (plen + (_data ==null ? k * part : Integer.parseInt(_data[k])) >= (k+1) * part ) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (log !=null) log.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
 
            });
            thread.start();
            // 把这5个线程保存到集合中
            list.add(thread);
        }
        long begin = System.currentTimeMillis();
        for(Thread t : list) {
            t.join(); // 将线程加入，并阻塞主线程
        }
        long end = System.currentTimeMillis();
        System.out.println("多线程传文件耗时：" + (end -begin) + "ms");
        // 读取完成后、将日志文件删除即可
        new File(logName).delete();
    }
 
    /**
     * 支持断点续传
     * @src 拷贝的原文件
     * @desc 拷贝的位置
     */
    public static void transportFile(File src, File desc) throws Exception {
        transportFile(src, desc, 5);
    }
 
    public static void transportFile(String src, String desc) throws Exception {
        transportFile(new File(src), new File(desc));
    }
}