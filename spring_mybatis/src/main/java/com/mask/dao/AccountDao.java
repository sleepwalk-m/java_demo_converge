package com.mask.dao;

import com.mask.domain.Account;

import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/06/25 22:28
 * @description:
 */
public interface AccountDao {

    void save(Account account);

    void update(Account account);

    void delete(Integer id);

    Account findById(Integer id);

    List<Account> findAll();
}
