package com.hhf.mvc.controller;

import com.hhf.mvc.bean.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class HttpController {

    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestBody) throws UnsupportedEncodingException {
        requestBody = URLDecoder.decode(requestBody, "UTF-8");
        System.out.println("requestBody:" + requestBody);
        return "success";
    }

    @RequestMapping("/testRequestEnity")
    public String testRequestEnity( RequestEntity<String> requestEntity) throws UnsupportedEncodingException {
        //RequestEntity表示整个请求报文的信息
        String body=URLDecoder.decode(requestEntity.getBody(),"UTF-8");
        System.out.println("请求头："+requestEntity.getHeaders());
        System.out.println("请求体："+body);
        return "success";
    }
    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse response) throws IOException {
        response.getWriter().println("hello response");
    }
    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody(){
        //使用注解直接在方法里面返回响应数据
        return "success";
    }
    @RequestMapping("/testResponseUser")
    @ResponseBody
    public User testResponseUser(){
        //使用注解直接在方法里面返回响应数据
        return new User(1001,"admin","1243",14,"男");
    }
    @RequestMapping("/testAxios")
    @ResponseBody
    public String testAxios(String username ,String password){
        System.out.println(username+","+password);
        return "hello,Axios";
    }
}