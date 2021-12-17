package com.hhf.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HP
 */

@Controller
public class HelloController {
    //当浏览器发送请求value是"/"的时候，就会执行该方法，该方法返回视图名称 inex就是对应视图前缀中路径，视图后缀的.html对应的index.html
    @RequestMapping( "/")
    public  String index(){
        //返回视图名称
        return "index";
    }
    @RequestMapping("/target")
    public String toTarget(){
        return "target";
    }
}
