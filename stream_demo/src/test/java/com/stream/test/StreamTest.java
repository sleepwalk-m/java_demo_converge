package com.stream.test;

import com.stream.entity.Person;
import org.junit.Before;
import org.junit.Test;

import javax.transaction.xa.XAException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: Mask.m
 * @create: 2021/10/08 18:03
 * @description: stream的测试
 */
public class StreamTest {

    List<Person> personList = new ArrayList<Person>();

    @Before
    public void testBefore() {
        personList.add(new Person("Tom", 8900,  10,"male", "New York"));
        personList.add(new Person("Jack", 7000, 20, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 15, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 65, "female", "New York"));
        personList.add(new Person("Owen", 9500, 32, "male", "New York"));
        personList.add(new Person("Alisa", 7900,18, "female", "New York"));
    }


    /**
     * stream的创建
     */
    @Test
    public void test1() {
        // 1. 集合创建流
        List<String> list = Arrays.asList("a", "b", "c");
        // 创建顺序流
        Stream<String> stream = list.stream();
        // 创建并行流
        Stream<String> parallelStream = list.parallelStream();

        // 2. 数组创建流
        int[] arr = {1, 3, 5, 7, 9};
        IntStream intStream = Arrays.stream(arr);

        // 3. stream静态方法创建流
        Stream<Integer> integerStream = Stream.of(1, 3, 5, 7, 9);

        // 从0开始 往上加3 提取前4个
        Stream<Integer> stream1 = Stream.iterate(0, x -> x + 3).limit(4);
        stream1.forEach(System.out::println);
        // 一般也用作生成随机数
        Stream<Double> stream2 = Stream.generate(Math::random).limit(3);
        stream2.forEach(System.out::println);
    }


    /**
     * 遍历 匹配
     */
    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

        // 遍历符合条件的
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> first = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> any = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean match = list.stream().anyMatch(integer -> integer >= 9);

        System.out.println("匹配第一个值：" + first.get());
        System.out.println("匹配任意一个值：" + any.get());
        System.out.println("是否存在大于6的值：" + match);
    }

    /**
     * 筛选 filter
     */
    @Test
    public void test3() {
        // 案例一 筛选出Integer集合中大于7的元素，并打印出来
        List<Integer> list = Arrays.asList(6, 7, 3, 8, 1, 2, 9);
        list.stream().filter(x -> x > 7).forEach(System.out::println);

        // 案例二  筛选员工中工资高于8000的人，并形成新的集合。 形成新集合依赖collect（收集），后文有详细介绍。
        List<String> collect = personList.stream().filter(person -> person.getSalary() > 8000)
                .map(Person::getName).collect(Collectors.toList());
        System.out.println("collect = " + collect);

    }

    /**
     * 聚合（max/min/count)
     */
    @Test
    public void test4() {
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");

        // 案例一：获取String集合中最长的元素。
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println("max = " + max.get());

        // 案例二：获取Integer集合中的最大值。
        List<Integer> list1 = Arrays.asList(7, 6, 9, 4, 11, 6);
        // 自然排序
        Optional<Integer> max1 = list1.stream().max(Integer::compareTo);
        System.out.println("max1 = " + max1);
        // 自定义排序
        Optional<Integer> max2 = list1.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println("自定义排序的最大值：" + max2.get());


        // 案例三：获取员工工资最高的人。
        Optional<Person> max3 = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("max3 = " + max3.get());

        // 案例四：计算Integer集合中大于6的元素的个数。
        List<Integer> list3 = Arrays.asList(7, 6, 4, 8, 2, 11, 9);

        long count = list3.stream().filter(integer -> integer > 6).count();
        System.out.println("count = " + count);

    }

    /**
     * 映射(map/flatMap)
     */
    @Test
    public void test5() {
        // 案例一：英文字符串数组的元素全部改为大写。整数数组每个元素+3。
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};

        List<String> strList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());


        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());

        System.out.println("每个元素大写：" + strList);
        System.out.println("每个元素+3：" + intListNew);


        // 案例二：将员工的薪资全部增加1000。
        // 不改变原集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0 ,person.getSex(), person.getArea());
            personNew.setSalary(person.getSalary() + 10000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前：" + personList.get(0).getName() + "-->" + personList.get(0).getSalary());
        System.out.println("一次改动后：" + personListNew.get(0).getName() + "-->" + personListNew.get(0).getSalary());

        // 改变原集合的方式
        List<Person> personListNew2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 10000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("二次改动前：" + personList.get(0).getName() + "-->" + personListNew.get(0).getSalary());
        System.out.println("二次改动后：" + personListNew2.get(0).getName() + "-->" + personListNew2.get(0).getSalary());


        // 案例三：将两个字符数组合并成一个新的字符数组。
        List<String> list = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listNew = list.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());


        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + listNew);

    }

    /**
     * 归约(reduce)
     * 归约，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
     */
    @Test
    public void test6() {
        // 案例一：求Integer集合的元素之和、乘积和最大值。
        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);

        // 求和
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        // 求最大值
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        Integer max2 = list.stream().reduce(0, Integer::max);


        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list求积：" + product.get());
        System.out.println("list求和：" + max.get() + "," + max2);


        // 案例二：求所有员工的工资之和和最高工资。

        // 工资之和
        Optional<Integer> sumSalary = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        Integer sumSalary2 = personList.stream().reduce(0, (sum4, p) -> sum4 += p.getSalary(), (x, y) -> x + y);
        Integer sumSalary3 = personList.stream().reduce(0, (sum4, p) -> sum4 += p.getSalary(), Integer::sum);

        // 最高工资
        Integer maxSalary = personList.stream().reduce(0, (maxSal, p) -> maxSal > p.getSalary() ? maxSal : p.getSalary(), (x, y) -> x > y ? x : y);

        Integer maxSalary2 = personList.stream().reduce(0, (maxSal, p) -> maxSal > p.getSalary() ? maxSal : p.getSalary(), Integer::max);

        System.out.println("工资之和：" + sumSalary.get() + "," + sumSalary2 + "," + sumSalary3);
        System.out.println("最高工资：" + maxSalary + "," + maxSalary2);

    }


    /**
     * 收集(collect) --> 归集(toList/toSet/toMap)
     */
    @Test
    public void test7(){
        List<Integer> list = Arrays.asList(1, 6, 3, 4, 6, 7, 9, 6, 20);
        List<Integer> listNew = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        Set<Integer> set = list.stream().filter(x -> x % 2 == 0).collect(Collectors.toSet());

        Map<?, Person> map = personList.stream().filter(p -> p.getSalary() > 8000)
                .collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("toList:" + listNew);
        System.out.println("toSet:" + set);
        System.out.println("toMap:" + map);
    }


    /**
     * 收集(collect) --> 统计(count/averaging)
     *
     *  Collectors提供了一系列用于数据统计的静态方法：
     *      计数：count
     *      平均值：averagingInt、averagingLong、averagingDouble
     *      最值：maxBy、minBy
     *      求和：summingInt、summingLong、summingDouble
     *      统计以上所有：summarizingInt、summarizingLong、summarizingDouble
     */
    @Test
    public void test8(){
        // 案例：统计员工人数、平均工资、工资总额、最高工资。

        // 员工总人数
        Long count = personList.stream().collect(Collectors.counting());
        // 第2种方式 计算总数
        Long count2 = personList.stream().count();
        // 第3种方式 计算总数
        int count3 = personList.stream().collect(Collectors.toList()).size();


        // 求平均工资
        Double average  = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));

        // 求最高工资
        Optional<Integer> max  = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));

        // 求工资之和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));

        // 一次性统计所有信息 统计出来person对象的工资 个数，总和，平均数，最大，最小
        /**
         * 员工工资所有统计：DoubleSummaryStatistics{count=6, sum=49300.000000, min=7000.000000, average=8216.666667, max=9500.000000}
         */
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));


        System.out.println("员工总数：" + count);
        System.out.println("员工平均工资：" + average);
        System.out.println("最高工资：" + max);
        System.out.println("员工工资总和：" + sum);
        System.out.println("员工工资所有统计：" + collect);
    }

    /**
     * 收集(collect) --> 分组(partitioningBy/groupingBy)
     * partitioningBy 分区
     * groupingBy 分组
     *
     */
    @Test
    public void test9(){
        // 案例：将员工按薪资是否高于8000分为两部分；将员工按性别和地区分组

        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(o -> o.getSalary() > 8000));

        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));


        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));

        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);

    }


    /**
     * 收集(collect) --> 接合(joining)
     * joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
     */
    @Test
    public void test10(){
        String names = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);
        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }

    /**
     * 收集(collect) --> 归约(reducing)
     * Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
     */
    @Test
    public void test11(){

        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和：" + sum);


        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());


    }

    /**
     * 收集(collect) --> 排序(sorted)
     * sorted，中间操作。有两种排序：
     *
     * sorted()：自然排序，流中元素需实现Comparable接口
     * sorted(Comparator com)：Comparator排序器自定义排序
     */
    @Test
    public void test12(){

        // 案例：将员工按工资由高到低（工资一样则按年龄由大到小）排序

        // 按工资升序排序（自然排序）
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName).collect(Collectors.toList());


        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).map(Person::getName).collect(Collectors.toList());


        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream().sorted(Comparator.comparing(Person::getSalary).thenComparing(Comparator.comparing(Person::getAge))).map(Person::getName).collect(Collectors.toList());


        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream().sorted((p1, p2) -> {
            if (p2.getSalary() == p1.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());

        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }

    /**
     * 提取/组合
     * 流也可以进行合并、去重、限制、跳过等操作。
     */
    @Test
    public void test13(){
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);


        // concat:合并两个流 distinct：去重
        List<String> newList  = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());

        // limit：限制从流中获得前n个数据
        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());

        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(5).limit(10).collect(Collectors.toList());

        System.out.println("流合并：" + newList);
        System.out.println("limit：" + collect);
        System.out.println("skip：" + collect2);
    }

}
