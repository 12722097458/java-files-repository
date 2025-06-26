package com.igeek.ssm.mapper;

import com.igeek.ssm.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /*保存信息
    * */
    public List<User> selectAll();

    public User selectByUserUid(Integer uid);

    public Integer updateUserInfo(User user);
}
