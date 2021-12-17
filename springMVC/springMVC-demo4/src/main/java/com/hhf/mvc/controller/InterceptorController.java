package com.hhf.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HP
 * 拦截器的演示
 */
@Controller
public class InterceptorController {
    @RequestMapping("/testinterceptor")
    public String testInterceptor(){
        return "success";
    }
}
