package com.igeek.ssm.service;

import com.igeek.ssm.domain.User;

import java.util.List;

public interface UserService {
    List<User> selectAll();
    int saveUser(User user);
    User selectUserById(int id);
}
