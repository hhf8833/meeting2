package com.hhf.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author HP
 */

@Controller
public class TestController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/param")
    public String testparam(){
        return "test_param";
    }
}
