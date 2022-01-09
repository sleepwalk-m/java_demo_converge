package com.mask.reflex.clazz;

import com.mask.reflex.entity.Cat;
import com.mask.reflex.entity.Dog;

/**
 * @author: Mask.m
 * @create: 2022/01/09 19:07
 * @description:
 */
public class Class01 {
    public static void main(String[] args) {
        /*
            1. 对象是类加载器 加载的 在堆中，并非是new出来
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return loadClass(name, false);
            }
         */
        Class<Cat> clazz = Cat.class;

        // 2. 某个类的Class类对象，内存中只有一份，因为只加载一次
        Class<Cat> clazz1 = Cat.class;
        Class<Dog> dogClass = Dog.class;

        System.out.println(clazz.hashCode());// 同一个类的 hashcode相同
        System.out.println(clazz1.hashCode());// 同一个类的hashcode相同

        System.out.println(dogClass.hashCode());// 不同类的hashcode相同


        // 3. 每个对象示例记得是由哪个Class类对象 来的
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        // 2个对象都能知道从哪个class来的
        System.out.println(cat1.getClass());
        System.out.println(cat2.getClass());


        // 4. 通过clazz对象可以完整得到一个类的完整结构
        //clazz.getMethod();
        //clazz.getDeclaredMethod();
        //clazz.getInterfaces();
        //....

    }
}
