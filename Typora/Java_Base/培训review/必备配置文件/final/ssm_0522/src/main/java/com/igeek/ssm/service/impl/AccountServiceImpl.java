package com.igeek.ssm.service.impl;

import com.igeek.ssm.domain.Account;
import com.igeek.ssm.mapper.AccountMapper;
import com.igeek.ssm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void transferMoney(int sourceId, int targetId, double money) {
        //查出转账人信息
        //修改转账人金钱
        //查出转账人信息
        //修改转账人金钱
        Account sourceAccount = accountMapper.selectAccountById(sourceId);
        Account targetAccount = accountMapper.selectAccountById(targetId);
        sourceAccount.setUmoney(sourceAccount.getUmoney()-money);
        targetAccount.setUmoney(targetAccount.getUmoney()+money);
        int i = accountMapper.updateAccount(sourceAccount);
        //int w = 1/0;
        int i1 = accountMapper.updateAccount(targetAccount);
        System.out.println(i);
        System.out.println(i1);


    }
}
