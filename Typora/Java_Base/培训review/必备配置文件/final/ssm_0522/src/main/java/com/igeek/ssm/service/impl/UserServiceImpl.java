package com.igeek.ssm.service.impl;

import com.igeek.ssm.domain.User;
import com.igeek.ssm.mapper.UserMapper;
import com.igeek.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Override
    public List<User> selectAll() {
        System.out.println("UserServiceImpl.selectAll()...");
        List<User> users = userMapper.selectAllUsers();
        return users;
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public int saveUser(User user) {
        System.out.println("进入UserServiceImpl.saveUser()...");
        int userid = user.getUserid();
        User user1 = userService.selectUserById(userid);
        if (user1 != null) {
            //主键冲突
            return -1;
        }
        return userMapper.addUser(user);
    }


}
