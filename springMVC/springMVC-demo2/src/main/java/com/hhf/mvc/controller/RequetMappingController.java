package com.hhf.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class RequetMappingController {
//    method = {RequestMethod.GET,RequestMethod.POST} 是指定value转发跳转的时候，能用哪些方法，不指定的话默认get post都行
    @RequestMapping(
            value = {"/testRequestMapping1","/testRequestMapping2"},
            method = {RequestMethod.GET,RequestMethod.POST}
    )
    public String success(){
        return "success";
    }
    @GetMapping("/testGetMapping")
    public String testGetMapping(){
        return "success";
    }

    @RequestMapping(value = "/testParamAndHeaders", params = {"username","password"})
    public String testParamAndHeaders(){
        return "success";
    }
    @RequestMapping("/a?a/testAnt")
    public String testAnt(){
        return "success";
    }
    @RequestMapping("/testRest/{id}")
    public String testRest(@PathVariable("id")Integer id){
        System.out.println("id"+id);
        return "success";
    }
//    @RequestMapping("/param")
//    public String testparam(){
//        return "test_param";
//    }
}
