package com.igeek.ssm.utils;

import com.igeek.ssm.domain.MyException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        MyException myException = new MyException();
        if (e instanceof  MyException) {
            myException = (MyException) e;
        } else {
            myException = new MyException("系统出错，请稍后重试！");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",myException.getMsg());
        mv.setViewName("error");
        return mv;
    }
}
