package com.hhf.spring5.service;

import com.hhf.spring5.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//注解里面的value可以不写，默认是类名称，首字母小写
//@Component(value = "userService") //<bean id="userService" class = "...."/>
@Service
public class UserService {
/*    //不需要set方法，属性注入
    //根据UserDao找到实现类，Autowired自动注入
    qualifier 要和autowired一起使用
    */

    @Autowired
    @Qualifier(value = "userDaoImp")
    private UserDao userDao;

    public void add(){
        System.out.println("service add....");
        userDao.add();
    }
}
