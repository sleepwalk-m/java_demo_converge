package com.mask;

import com.mask.domain.Account;
import com.mask.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/06/25 22:43
 * @description:
 */
public class UserApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService accountService = (AccountService) ctx.getBean("accountService");
        List<Account> all = accountService.findAll();
        System.out.println("all = " + all);

        Account byId = accountService.findById(2);
        System.out.println("byId = " + byId);

    }
}
