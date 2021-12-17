package com.hhf.spring5.testdemo;

import com.hhf.spring5.aopanno.User;
import com.hhf.spring5.config.SpringConfig;
import com.hhf.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemo {
    @Test
    public void testService (){
        ApplicationContext context =new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.add();
    }

    @Test
    public void testService2 (){
        //采用配置类
        ApplicationContext context =new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.add();
    }
    @Test
    public void testService3 (){
        //采用配置类
        ApplicationContext context =new ClassPathXmlApplicationContext("bean1.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.add();
    }
}
