package com.mask.ssm.service.impl;

import com.mask.ssm.dao.AccountDao;
import com.mask.ssm.domain.Account;
import com.mask.ssm.service.AccountService;

import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/06/25 22:27
 * @description:
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void save(Account account) {
        accountDao.save(account);
    }

    public void update(Account account) {
accountDao.update(account);
    }

    public void delete(Integer id) {
        accountDao.delete(id);
    }

    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    public List<Account> findAll() {
        return accountDao.findAll();
    }
}
