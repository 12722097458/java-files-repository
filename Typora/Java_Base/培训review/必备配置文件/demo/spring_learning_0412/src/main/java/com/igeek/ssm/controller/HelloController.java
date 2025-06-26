package com.igeek.ssm.controller;

import com.igeek.ssm.domain.SysException;
import com.igeek.ssm.domain.User;
import com.igeek.ssm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class HelloController {

    @Autowired
    private UserMapper userMapper;
    @RequestMapping(path = "/sayHello")
    public void sayHello(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("进入HelloController.sayHello().....");
        List<User> users = userMapper.selectAll();
        for(User user:users){
            System.out.println(user);
            System.out.println("_________________");
        }
        request.setAttribute("selectedUsers",users);
        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);
    }

    @RequestMapping(path = "/testException")
    public String testException() throws SysException {
        System.out.println("进入HelloController.testException().....");
        try {
            // int a = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException("查询出错!!!");
        } finally {
        }
        return "success";
    }
}
