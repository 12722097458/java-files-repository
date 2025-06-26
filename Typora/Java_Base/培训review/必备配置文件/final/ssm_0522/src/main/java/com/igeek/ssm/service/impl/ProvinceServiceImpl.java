package com.igeek.ssm.service.impl;

import com.igeek.ssm.domain.Provinces;
import com.igeek.ssm.mapper.ProvincesMapper;
import com.igeek.ssm.service.ProvinceService;
import com.igeek.ssm.utils.JedisPoolUtils;
import com.igeek.ssm.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvincesMapper provincesMapper;

    @Override
    public List<Provinces> selectAll() {
        return provincesMapper.selectAll();
    }

    @Override
    public String getRedisProvince() {
        Jedis jedis = JedisPoolUtils.getJedis();
        String province_json = jedis.get("province");
        if (province_json==null || province_json.length()==0) {
            System.out.println("缓存中没有数据，需要查询数据库并存入redis缓存。");
            List<Provinces> provinces = this.selectAll();
            province_json = JsonUtils.getJson(provinces);
            jedis.set("province",province_json);
            jedis.close();
        } else {
            System.out.println("缓存有数据，查询的是数据库。");
        }
        return province_json;
    }
}
