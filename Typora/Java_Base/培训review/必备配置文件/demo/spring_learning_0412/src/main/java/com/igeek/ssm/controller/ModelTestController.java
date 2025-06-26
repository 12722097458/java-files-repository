package com.igeek.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping(value = "/model")
@SessionAttributes(value = {"msg"})           //把msg=米呃呃  存入到session的作用域中
public class ModelTestController {

    /*
        存session值
    * */
    @RequestMapping(value = "/testsessionAttribute")
    public String testsessionAttribute(Model model){
        System.out.println("ModelTestController.testsessionAttribute()方法。。。");
        //底层默认存储到request域对象中
        model.addAttribute("msg","米呃呃");
        return "success";
    }

    /*
        取session值
    * */
    @RequestMapping(value = "/getSessionAttribute")
    public String getSessionAttribute(ModelMap model){
        System.out.println("ModelTestController.getSessionAttribute()方法。。。");
        Object msg = model.get("msg");
        System.out.println(msg);
        return "success";
    }

    /*
        删除session值
    * */
    @RequestMapping(value = "/deleteSessionAttribute")
    public String deleteSessionAttribute(SessionStatus sessionStatus){
        System.out.println("ModelTestController.deleteSessionAttribute()方法。。。");
        sessionStatus.setComplete();
        return "success";
    }
}
