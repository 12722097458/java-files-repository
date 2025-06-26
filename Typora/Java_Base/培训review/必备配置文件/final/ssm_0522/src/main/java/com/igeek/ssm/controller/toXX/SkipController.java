package com.igeek.ssm.controller.toXX;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/skip")
public class SkipController {

    @RequestMapping(path = "/toDateFormatTest")
    public ModelAndView toDateFormatTest(){
        System.out.println("SkipController.toDateFormatTest()...");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userpages/dateFormate");
        return mv;
    }

}
