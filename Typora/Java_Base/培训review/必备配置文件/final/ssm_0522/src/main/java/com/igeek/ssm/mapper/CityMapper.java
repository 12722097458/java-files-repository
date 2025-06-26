package com.igeek.ssm.mapper;

import com.igeek.ssm.domain.Cities;

import java.util.List;

public interface CityMapper {

    List<Cities> selectCitiesById(String id);
}
