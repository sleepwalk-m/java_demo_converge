package com.drools;

import com.drools.entity.ComparisonOperatorEntity;
import com.drools.entity.Order;
import com.drools.entity.Student;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Arrays;

/**
 * @author: Mask.m
 * @create: 2021/11/21 13:02
 * @description: 测试
 */
public class DroolsTest {

    @Test
    public void test(){

        // 获取services对象
        KieServices kieServices = KieServices.Factory.get();
        // 获取容器
        KieContainer container = kieServices.getKieClasspathContainer();
        // 获取会话
        KieSession kieSession = container.newKieSession();

        // Fact对象 就是事实对象
        Order order = new Order();
        order.setOriginalPrice(500D);
        // 执行insert 到工作内存中
        kieSession.insert(order);

        // 激活规则 由drools来做 匹配所有规则
        System.out.println("激活规则前：" + order.getRealPrice());
        kieSession.fireAllRules();
        System.out.println("激活规则后：" + order.getRealPrice());

        // 关闭会话
        kieSession.dispose();
    }

    /**
     * 测试比较操作符
     */
    @Test
    public void test1(){

        // 获取services对象
        KieServices kieServices = KieServices.Factory.get();
        // 获取容器
        KieContainer container = kieServices.getKieClasspathContainer();
        // 获取会话
        KieSession kieSession = container.newKieSession();

        // Fact对象 就是事实对象
        ComparisonOperatorEntity fact = new ComparisonOperatorEntity();
        fact.setNames("张三");
        fact.setList(Arrays.asList(fact.getNames(),"李四","王五"));
        // 执行insert 到工作内存中
        kieSession.insert(fact);

        // 激活规则 由drools来做 匹配所有规则
        kieSession.fireAllRules();

        // 关闭会话
        kieSession.dispose();
    }

    /**
     * 测试指定 规则触发
     */
    @Test
    public void test2(){

        // 获取services对象
        KieServices kieServices = KieServices.Factory.get();
        // 获取容器
        KieContainer container = kieServices.getKieClasspathContainer();
        // 获取会话
        KieSession kieSession = container.newKieSession();

        // Fact对象 就是事实对象
        ComparisonOperatorEntity fact = new ComparisonOperatorEntity();
        fact.setNames("张三");
        fact.setList(Arrays.asList(fact.getNames(),"李四","王五"));
        // 执行insert 到工作内存中
        kieSession.insert(fact);

        // 激活规则 由drools来做 匹配所有规则   规定指定的规则执行
        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_comparison_notmatches"));

        // 关闭会话
        kieSession.dispose();
    }


    /**
     * 测试 drools内置方法 insert update retract
     */
    @Test
    public void test3(){

        // 获取services对象
        KieServices kieServices = KieServices.Factory.get();
        // 获取容器
        KieContainer container = kieServices.getKieClasspathContainer();
        // 获取会话
        KieSession kieSession = container.newKieSession();

        // Fact对象 就是事实对象
        Student fact = new Student();
        fact.setAge(5);
        // 执行insert 到工作内存中
        kieSession.insert(fact);

        // 激活规则 由drools来做 匹配所有规则   规定指定的规则执行
        kieSession.fireAllRules();
        System.out.println();
        // 关闭会话
        kieSession.dispose();
    }

    /**
     * 测试 drools内置方法  update
     */
    @Test
    public void test4(){

        // 获取services对象
        KieServices kieServices = KieServices.Factory.get();
        // 获取容器
        KieContainer container = kieServices.getKieClasspathContainer();
        // 获取会话
        KieSession kieSession = container.newKieSession();

        // Fact对象 就是事实对象
        Student fact = new Student();
        fact.setAge(10);
        // 执行insert 到工作内存中
        kieSession.insert(fact);

        // 激活规则 由drools来做 匹配所有规则   规定指定的规则执行
        kieSession.fireAllRules();
        System.out.println();
        // 关闭会话
        kieSession.dispose();
    }

    /**
     * 测试 drools内置方法 retract
     */
    @Test
    public void test5(){

        // 获取services对象
        KieServices kieServices = KieServices.Factory.get();
        // 获取容器
        KieContainer container = kieServices.getKieClasspathContainer();
        // 获取会话
        KieSession kieSession = container.newKieSession();

        // Fact对象 就是事实对象
        Student fact = new Student();
        fact.setAge(10);
        // 执行insert 到工作内存中
        kieSession.insert(fact);

        // 激活规则 由drools来做 匹配所有规则   规定指定的规则执行
        kieSession.fireAllRules();
        System.out.println();
        // 关闭会话
        kieSession.dispose();
    }
}
