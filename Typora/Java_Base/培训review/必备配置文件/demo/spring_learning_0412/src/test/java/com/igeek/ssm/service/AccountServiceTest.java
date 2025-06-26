package com.igeek.ssm.service;

import com.igeek.ssm.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class AccountServiceTest {
    @Autowired
    private AccountServiceImpl accountService;

    @Test
    public void transferTest(){
        System.out.println("进入AccountServiceTest.transferTest().......");
        Integer source = 1;   //Jack
        Integer target = 2;   //Rose
        Double money = 100d;
        accountService.transfer(source,target,money);
    }
}
