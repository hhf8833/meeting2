package com.hhf.spring5.testdemo;

import com.hhf.spring5.bean.Dept;
import com.hhf.spring5.bean.Orders;
import com.hhf.spring5.factorybean.MyBean;
import com.hhf.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestBean {
    @Test
    public void TestAdd(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        UserService userService = context.getBean("userService",UserService.class);
        userService.add();
    }
    @Test
    public void TestMyBean(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        Dept dept = context.getBean("myBean", Dept.class);
        System.out.println(dept);;
    }
    @Test
    public void TestMyBean2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println("4. 获取bean的对像");
        System.out.println(orders);;

        ((ClassPathXmlApplicationContext)context).close();

    }
}
