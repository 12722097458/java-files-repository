package com.igeek.shop.test;

import com.igeek.shop.entity.UserExample;
import com.igeek.shop.mapper.UserMapper;

import java.util.Date;

public class UserMapperTest {

    public static void main(String[] args){

        String name = "a";

        UserMapper userMapper = null;

        UserExample condition = new UserExample();
        //是否去重
        //condition.setDistinct(true);

        //查询的排序
        condition.setOrderByClause("birthday desc,uid desc");

        UserExample.Criteria conditionCriteria = condition.createCriteria();
        conditionCriteria.andUsernameLike("%"+name+"%");
        conditionCriteria.andSexEqualTo("男");
        conditionCriteria.andBirthdayLessThanOrEqualTo(new Date());

        userMapper.selectByExample(condition);

    }
}
