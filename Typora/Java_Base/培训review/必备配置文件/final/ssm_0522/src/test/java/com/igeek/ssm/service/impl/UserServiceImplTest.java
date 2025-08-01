package com.igeek.ssm.service.impl;

import com.igeek.ssm.domain.User;
import com.igeek.ssm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

//springjunit集成测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void testSelectAll(){
        List<User> users = userService.selectAll();
        System.out.println(users);
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setUserid(666);
        user.setUsername("测试");
        user.setPassword("888");
        user.setBirthday(new Date());
        int i = userService.saveUser(user);
        System.out.println(i);
    }
}
