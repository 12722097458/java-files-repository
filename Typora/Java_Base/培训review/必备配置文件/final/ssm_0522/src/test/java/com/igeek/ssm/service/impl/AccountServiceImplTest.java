package com.igeek.ssm.service.impl;

import com.igeek.ssm.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//springjunit集成测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
public class AccountServiceImplTest {
    @Autowired
    AccountService accountService;

    @Test
    public void testTransferMoney(){
        accountService.transferMoney(1,2,100d);
    }

}
