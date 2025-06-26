package com.igeek.ssm.service;

import com.igeek.ssm.domain.Cities;

import java.util.List;

public interface CityService {
    List<Cities> selectAllCitiseById(String id);

}
