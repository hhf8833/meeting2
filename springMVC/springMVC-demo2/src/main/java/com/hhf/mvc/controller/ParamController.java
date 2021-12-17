package com.hhf.mvc.controller;


import com.hhf.mvc.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ParamController {
    //原生servletapi
    @RequestMapping("/testServletRequest")
    public String testparam(HttpServletRequest request){
        //HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:"+username+",password:"+password);
        return "success";
    }
    @RequestMapping("/testparam")
    public String testparam( String username,String password){
        System.out.println("username:"+username+",password:"+password);
        return "success";
    }
    @RequestMapping("/testparam2")
    //RequestParam里面的参数是前端传来的参数
    public String testparam(
            @RequestParam("user_name") String username,
            String password,
            @RequestParam(required = false,defaultValue = "没有爱好") String hobby,
            @RequestHeader(value = "Host21212",required = false,defaultValue = "haha") String host,
            @CookieValue(value = "JSESSIONID",required = false,defaultValue = "暂无cookie")String jsseionid){
        System.out.println("username:"+username+",password:"+password+",爱好:"+hobby);
        System.out.println("host："+host);
        System.out.println("jsseionid："+jsseionid);
        return "success";
    }
    @RequestMapping("/testpojo")
    public String testpojo(User user){
        System.out.println(user);

        return "success";
    }
}
