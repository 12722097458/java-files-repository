package com.igeek.ssm.mapper;

import com.igeek.ssm.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//springjunit集成测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelecaAll(){
        List<User> userList = userMapper.selectAll();
        for(User user:userList){
            System.out.println(user);
        }
    }

}
