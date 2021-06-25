package com.mask.service;

import com.mask.domain.Account;

import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/06/25 22:26
 * @description:
 */
public interface AccountService {

    void save(Account account);

    void update(Account account);

    void delete(Integer id);

    Account findById(Integer id);

    List<Account> findAll();
}
