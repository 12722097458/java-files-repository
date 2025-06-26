package com.igeek.ssm.mapper;

import com.igeek.ssm.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//springjunit集成测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
public class AccountMapperTest {
    @Autowired
    AccountMapper accountMapper;

    @Test
    public void testSelectAll(){
        List<Account> accounts = accountMapper.selectAllAccounts();
        System.out.println(accounts);
    }

    @Test
    public void testSelectOne(){
        Account account = accountMapper.selectAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testUpdateAccount(){
        Account account = accountMapper.selectAccountById(1);
        account.setUmoney(1000);
        accountMapper.updateAccount(account);
        System.out.println(accountMapper.selectAccountById(1));
    }
}
