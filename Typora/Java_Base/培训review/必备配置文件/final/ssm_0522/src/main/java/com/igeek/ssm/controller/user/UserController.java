package com.igeek.ssm.controller.user;

import com.igeek.ssm.domain.User;
import com.igeek.ssm.service.UserService;
import com.igeek.ssm.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/doSaveUser")
    @ResponseBody
    public String doSaveUser(User user, HttpServletRequest request, HttpServletResponse response){
        System.out.println("进入UserController.doSaveUser()......");
        System.out.println(user);
        //存储  -1 失败，1 成功  ， 0 异常
        int result = userService.saveUser(user);

        Cookie[] cookies = request.getCookies();
        int temp = 0;
        for (Cookie cookie : cookies) {
            if ("uid_cookie".equals(cookie.getName())) {
                cookie.setValue(user.getUserid()+"");
                cookie.setMaxAge(60);
                response.addCookie(cookie);
                temp = 1;
                break;
            }
        }
        if (temp == 0) {
            Cookie cookie = new Cookie("uid_cookie",user.getUserid()+"");
            cookie.setMaxAge(60);
            response.addCookie(cookie);
        }
        return JsonUtils.getJson(result);
    }

    //对userid进行判断
    @RequestMapping(path = "/checkUserId")
    @ResponseBody
    public String checkUserId(int userid){
        System.out.println("进入UserController.checkUserId()......");
        System.out.println(userid);
        User user = userService.selectUserById(userid);
        HashMap<String,String> map = new HashMap<>();
        if (user!=null) {
            map.put("ifExists","1");
            map.put("msg","当前用户已被注册，请进行修改!");
        } else {
            map.put("ifExists","0");
            map.put("msg","当前用户可用！");
        }
        return JsonUtils.getJson(map);
    }

    //对userid进行判断
    @RequestMapping(path = "/showUserId")
    @ResponseBody
    public String showUserId(HttpServletRequest request){
        String resultValue = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("uid_cookie".equals(cookie.getName())) {
                resultValue = cookie.getValue();
                break;
            }
        }
        Map<String,String> map = new HashMap<>();
        map.put("isId",resultValue);
        return JsonUtils.getJson(map);
    }
}
