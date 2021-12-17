package com.hhf.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ScopeController {

    @RequestMapping("/testRequestServletAPI")
    public String testRequestServletAPI(HttpServletRequest request){
        request.setAttribute("testRequestScope","hello,servlet");
        return "success";
    }
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(ModelAndView mav){

        //处理模型数据，即向请求域request共享数据
        mav.addObject("testRequestScope","hello,testModelAndView");
        mav.setViewName("success");
        return mav;
    }
    @RequestMapping("/testModel")
    public String  testModel(Model model){
        model.addAttribute("testRequestScope","hello,model");
        return "success";
    }
    @RequestMapping("/testSession")
    public String  testSession(HttpSession session){
        session.setAttribute("testSessionScope","hello,session");
        return "success";
    }
    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session){
        ServletContext application = session.getServletContext();
        application.setAttribute("testApplicationScope","hello,application");
        return "success";
    }
}
