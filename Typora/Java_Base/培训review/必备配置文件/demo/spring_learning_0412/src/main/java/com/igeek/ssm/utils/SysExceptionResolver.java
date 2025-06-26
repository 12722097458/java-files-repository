package com.igeek.ssm.utils;

import com.igeek.ssm.domain.SysException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        SysException sysException = null;
        if (e instanceof SysException) {
            sysException = (SysException) e;
        } else {
            sysException = new SysException("系统正在维护。。。");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg",sysException.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
