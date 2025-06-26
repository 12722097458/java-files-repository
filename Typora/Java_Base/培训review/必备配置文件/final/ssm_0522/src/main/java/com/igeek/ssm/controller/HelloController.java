package com.igeek.ssm.controller;

import com.igeek.ssm.domain.Cities;
import com.igeek.ssm.domain.MyException;
import com.igeek.ssm.service.CityService;
import com.igeek.ssm.service.ProvinceService;
import com.igeek.ssm.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "/hello")
public class HelloController {
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;

    @RequestMapping(path = "/sayHello")
    public String sayHello(){
        System.out.println("进入HelloController.sayHello().....");

        return "success";
    }

    /*
    * 异常测试
    * */
    @RequestMapping(path = "/testException")
    public String testException() throws MyException {
        System.out.println("进入HelloController.testException().....");
        try {
            int a = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException("查询出错!!!");
        } finally {
        }
        return "success";
    }

    @RequestMapping(path = "/testSensitiveWordsFilter")
    public String testSensitiveWordsFilter(HttpServletRequest request) {
        String name = request.getParameter("name");
        String words = request.getParameter("words");
        System.out.println("进入HelloController.testSensitiveWordsFilter().....");
        System.out.println(name+"说："+words);
        return "success";
    }

    //三级联动获取省份
    @RequestMapping(path = "/getProvince")
    @ResponseBody
    public String testGetProvince() {
        return provinceService.getRedisProvince();
    }

    //三级联动获取城市
    @RequestMapping(path = "/getCities")
    @ResponseBody
    public String testGetCities(HttpServletRequest request) {
        String provinceid = request.getParameter("provinceid");
        System.out.println(provinceid);
        List<Cities> cities = cityService.selectAllCitiseById(provinceid);
        return JsonUtils.getJson(cities);
    }

}
