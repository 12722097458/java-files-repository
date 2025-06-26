package com.igeek.ssm.service;

import com.igeek.ssm.domain.Provinces;

import java.util.List;

public interface ProvinceService {
    List<Provinces> selectAll();

    String getRedisProvince();
}
