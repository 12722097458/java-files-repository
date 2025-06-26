package com.igeek.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/modelAndView")
public class ModelAndViewTestController {

    /*@RequestMapping(value = "/testForwardOrRedirect")
    public ModelAndView testForwardOrRedirect(){
        System.out.println("ModelAndViewTestController.testForwardOrRedirect()方法。。。");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","Jack");
        modelAndView.setViewName("success");
        return modelAndView;
    }*/

    @RequestMapping(value = "/testModelAndView")
    public ModelAndView testModelAndView(){
        System.out.println("ModelAndViewTestController.testModelAndView()方法。。。");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","Jack");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping(value = "/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        System.out.println("ModelAndViewTestController.testForwardOrRedirect()方法。。。");
        //return "forward:/WEB-INF/pages/success2.jsp";     //请求转发     一般是密码输入错误，返回一个提示
        return "redirect:/index.jsp";                       //响应重定向   一般是登录成功，避免重复提交
    }

}
