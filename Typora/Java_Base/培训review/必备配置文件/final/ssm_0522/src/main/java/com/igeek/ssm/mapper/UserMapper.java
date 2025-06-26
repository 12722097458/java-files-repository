package com.igeek.ssm.mapper;

import com.igeek.ssm.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAllUsers();

    int addUser(User user);

    User selectUserById(int uid);
}
