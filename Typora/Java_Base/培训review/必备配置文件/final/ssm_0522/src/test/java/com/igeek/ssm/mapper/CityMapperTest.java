package com.igeek.ssm.mapper;

import com.igeek.ssm.domain.Cities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//springjunit集成测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
public class CityMapperTest {
    @Autowired
    CityMapper cityMapper;

    @Test
    public void testSelectById(){
        List<Cities> cities = cityMapper.selectCitiesById("410000");
        System.out.println(cities);
    }
}
