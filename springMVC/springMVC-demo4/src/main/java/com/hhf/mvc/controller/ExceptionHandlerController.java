package com.hhf.mvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author HP
 * 基于注解处理异常
 */
@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(value = {ArithmeticException.class,NullPointerException.class})
    public  String testException(Exception exception, Model model){
        model.addAttribute("ex",exception);
        return "error";
    }
}
