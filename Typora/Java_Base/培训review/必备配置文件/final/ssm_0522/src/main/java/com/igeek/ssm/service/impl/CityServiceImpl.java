package com.igeek.ssm.service.impl;

import com.igeek.ssm.domain.Cities;
import com.igeek.ssm.mapper.CityMapper;
import com.igeek.ssm.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;
    @Override
    public List<Cities> selectAllCitiseById(String id) {
        return cityMapper.selectCitiesById(id);
    }
}
