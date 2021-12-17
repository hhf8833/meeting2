package com.hhf.spring5.testdemo;

import com.hhf.spring5.Book;
import com.hhf.spring5.User;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring5 {
    @Test
    public void testAdd(){
        //1/加载spring配置文件
//        BeanFactory context = new ClassPathXmlApplicationContext("bean1.xml");
        //2 获取配置的对象 ClassPathXmlApplicationContext 路径是src里面
        ApplicationContext  context = new ClassPathXmlApplicationContext("bean1.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
        user.add();
    }
    @Test
    public void testBook(){
        //1/加载spring配置文件
//        BeanFactory context = new ClassPathXmlApplicationContext("bean1.xml");
        //2 获取配置的对象 ClassPathXmlApplicationContext 路径是src里面
        ApplicationContext  context = new ClassPathXmlApplicationContext("bean1.xml");
        Book book = context.getBean("book", Book.class);
        System.out.println(book);
        book.toString();
    }
}
