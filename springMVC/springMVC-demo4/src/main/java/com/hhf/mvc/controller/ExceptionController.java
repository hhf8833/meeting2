package com.hhf.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {
    @RequestMapping("testExceptionHandler")
    public String testExceptionHandler(){
        System.out.println(1/0);
        return "success";
    }
}
