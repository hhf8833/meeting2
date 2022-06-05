package com.hhf.boot.controller;

import com.hhf.boot.bean.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jnlp.PersistenceService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestHandler {

    @ResponseBody
    @RequestMapping("/test/person")
    public Person getPeson(){
        Person person = new Person();
        person.setUserName("zhansan");
        person.setAge(18);
        person.setBirth(new Date());
        return person;
    }
    @RequestMapping("/goto")
    public String requestSevlet(HttpServletRequest request){
        request.setAttribute("para1","hello!!");
        request.setAttribute("msg","成功了");
        return "forward:/success";

    }
    @RequestMapping("/param")
    public String test2(Map<String,Object> map,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("redirectAttributes","重定向能不能得到呢");
        map.put("mapPara","hello world");
        model.addAttribute("modelPara" , "hello world 2");
        request.setAttribute("requestParam","hello world 3");
        Cookie cookie = new Cookie("c1","v1");
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        //使用redirect之后重定向是不能携带参数的
        return "redirect:/success";
    }
    @ResponseBody
    @RequestMapping("/success")
    public Map success(HttpServletRequest request,
                       @RequestAttribute(value = "msg",required = false) String msg,
                       Model model){
        Object modelPara1 = model.getAttribute("modelPara");
        Object msg1 = request.getAttribute("msg");
        Object mapPara = request.getAttribute("mapPara");
        Object modelPara = request.getAttribute("modelPara");
        Object redirectAttributes = request.getAttribute("redirectAttributes");
        System.out.println(redirectAttributes);
        Map<String,Object> map =new HashMap<>();
        map.put("servletAPI",msg1);
        map.put("annocation",msg);
        map.put("mapPara",mapPara);
        map.put("modelPara",modelPara);
        map.put("modelPara1",modelPara1);
        map.put("redirectAttributes",redirectAttributes);

        return map;

    }
}
