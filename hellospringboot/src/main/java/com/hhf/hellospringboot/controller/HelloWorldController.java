package com.hhf.hellospringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HP
 */

@RestController
@RequestMapping("test")
public class HelloWorldController {
    @GetMapping("hello")
    public String sayHello(){
        return "hello word";
    }
}
