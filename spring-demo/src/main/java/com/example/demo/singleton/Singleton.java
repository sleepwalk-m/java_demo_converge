package com.mask.m.singleton;

/**
 * @author wb-jf930343
 * @version 1.0
 * @date 2021/6/24 16:36
 * @Description: 手写单例模式-懒汉式
 */
public class Singleton {

    private static volatile Singleton singleton;// volatile关键字 防止计算机指令重排，这样可以保证是单例
    // 私有构造方法 不被外界创建
    private Singleton(){}

    public static Singleton getInstance(){

        // DLC: double check lock 双重校验锁
        // 1.第一次 a b两个线程进来
        if (singleton == null) {
            // 2.当A B都走到这里了    5.这时b线程继续执行
            synchronized (Singleton.class) {
                //3. a先到这里 b 在2等待    6.b线程到这里时。如果没有非空判断，那么仍然会创建一个对象，那就不是单例的了
                if (singleton == null) {
                    //4. a线程创建了singleton
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
