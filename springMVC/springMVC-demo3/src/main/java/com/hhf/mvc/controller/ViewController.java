package com.hhf.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/testThymeleafView")
    public String testThymeleafView(){
        return "success";
    }
    @RequestMapping("/testForwardView")
    public String testForward(){
        //转发到testThymeleafView控制器
        return "forward:/testThymeleafView";
    }
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        //转发到testThymeleafView控制器
        return "redirect:/testThymeleafView";
    }
}
