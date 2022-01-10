package com.mask.collection.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Mask.m
 * @create: 2021/12/19 15:17
 * @description:
 */
public class MapDemo {
    public static void main(String[] args) {
        Map map = new HashMap();

        map.put("邓超","孙俪");
        map.put("贾乃亮","李晓璐");
        map.put("王力宏","李靓蕾");

        // 遍历方式
        // 1. 取出所有key，再拿到value
        Set set = map.keySet();
        for (Object o : set) {
            System.out.println(o + " = " + map.get(o));
        }

        // 2. 取出所有的values
        Collection values = map.values();
        for (Object value : values) {
            System.out.println("value = " + value);
        }


        // 3. entryset 拿到kv值
        Set entrySet = map.entrySet();
        for (Object o : entrySet) {
            Map.Entry m = (Map.Entry) o;
            System.out.println( m.getKey() + "=" + m.getValue());
        }
    }
}
