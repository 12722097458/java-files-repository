package com.igeek.ssm.mapper;

import com.igeek.ssm.domain.Provinces;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//springjunit集成测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext.xml")
public class ProvinceMapperTest {
    @Autowired
    ProvincesMapper provincesMapper;

    @Test
    public void testSelectAll(){
        List<Provinces> provinces = provincesMapper.selectAll();
        System.out.println(provinces);
    }
}
