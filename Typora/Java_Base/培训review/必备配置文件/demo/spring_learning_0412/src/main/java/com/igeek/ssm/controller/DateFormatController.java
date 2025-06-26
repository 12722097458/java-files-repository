package com.igeek.ssm.controller;

import com.igeek.ssm.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/dateformat")
public class DateFormatController {

    @RequestMapping(value = "/saveUser")
    public String saveUser(User user){
        System.out.println("进入DateFormatController.saveUser()方法。。。");
        System.out.println(user);
        return "success";
    }

    @RequestMapping(value = "/doGetVariables")
    public String doGetVariables(@RequestParam(value = "name") String username){
        System.out.println("进入DateFormatController.doGetVariables()方法。。。");
        System.out.println(username);
        return "success";
    }

}
