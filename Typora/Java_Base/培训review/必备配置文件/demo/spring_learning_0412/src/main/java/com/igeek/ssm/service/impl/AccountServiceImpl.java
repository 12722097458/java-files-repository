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
    public void transfer(Integer source,Integer target,Double money) {
        System.out.println("进入AccountServiceImpl.transfer()......");
        //1、获取A的账户信息，减少100元
        Account account01 = accountMapper.selectById(source);
        account01.setMoney(account01.getMoney()-100);
        accountMapper.updateAccount(account01);
        //2、获取B的账户信息，增加100元
        Account account02 = accountMapper.selectById(target);
        account02.setMoney(account02.getMoney()+100);
        accountMapper.updateAccount(account02);
    }
}
