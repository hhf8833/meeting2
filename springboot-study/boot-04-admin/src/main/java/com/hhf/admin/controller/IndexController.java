package com.hhf.admin.controller;

import com.hhf.admin.bean.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@Log4j2
@Controller
public class IndexController {

    @RequestMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model){
        if (StringUtils.hasLength(user.getUserName()) &&StringUtils.hasLength(user.getPassword())){
            session.setAttribute("loginUser",user);
            //使用重定向，防止表单重复提交
            return "redirect:/main";
        }else {

            model.addAttribute("err","账号密码错误");
            return "login";
        }


    }

    @RequestMapping("/main")
    public  String main(HttpSession session,Model model){
        log.info("当前方法是：","main");
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null){
            return "main";
        }else {
            model.addAttribute("err","请重新登录");
            return "login";
        }
    }
}


