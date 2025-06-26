package com.igeek.ssm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.igeek.ssm.domain.User;
import com.igeek.ssm.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class JsonController  {

    @RequestMapping(value = "/jsonLearning")   //,produces = {"application/json;charset=UTF-8"}
    @ResponseBody              //将服务器端的对象转换成json对象相应回去
    public String jsonLearning() throws JsonProcessingException {
        User user = new User();
        user.setUserId(3);
        user.setUsername("尹君");
        user.setPassword("123456");
        user.setBirthday(new Date());
        //创建一个jackson对象，用来转换json格式    jackson的对象映射器
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(user);
        System.out.println(string);
        return string;   //由于使用了jackson的@ResponseBody，这里将string以json字符串格式返回，而不进入视图解析器
    }
    @RequestMapping(value = "/jsonLearningList")   //,produces = {"application/json;charset=UTF-8"}
    @ResponseBody              //将服务器端的对象转换成json对象相应回去
    public String jsonLearningList() throws JsonProcessingException {
        User user = new User();
        user.setUserId(3);
        user.setUsername("尹君");
        user.setPassword("123456");
        user.setBirthday(new Date());
        List list = new ArrayList();
        list.add(user);
        list.add(user);
        list.add(user);

        //创建一个jackson对象，用来转换json格式    jackson的对象映射器
        ObjectMapper objectMapper = new ObjectMapper();
        String string = objectMapper.writeValueAsString(list);
        System.out.println(string);
        return string;   //由于使用了jackson的@ResponseBody，这里将string以json字符串格式返回，而不进入视图解析器
    }

    @RequestMapping(value = "/jsonTime")   //,produces = {"application/json;charset=UTF-8"}
    @ResponseBody              //将服务器端的对象转换成json对象相应回去
    public String jsonTime() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        objectMapper.setDateFormat(sdf);
        String string = objectMapper.writeValueAsString(new Date());
        System.out.println(string);
        return string;   //由于使用了jackson的@ResponseBody，这里将string以json字符串格式返回，而不进入视图解析器
    }

    @RequestMapping(value = "/jsonTime2")   //,produces = {"application/json;charset=UTF-8"}
    @ResponseBody              //将服务器端的对象转换成json对象相应回去
    public String jsonTime2() throws JsonProcessingException {

        return JsonUtils.getJson(new Date());   //由于使用了jackson的@ResponseBody，这里将string以json字符串格式返回，而不进入视图解析器
    }












    @RequestMapping(path = "/jsonTest")
    public @ResponseBody String jsonTest(User body){
        System.out.println("进入JsonController.jsonTest()...");
        System.out.println(body);
        body.setUserId(8888888);
        return JsonUtils.getJson(body);
    }








}
