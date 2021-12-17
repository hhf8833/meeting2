package com.hhf.spring5.test;

import com.hhf.spring5.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/*@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:bean1.xml")*/
//对上面两个进行简化
@SpringJUnitConfig(locations = "classpath:bean1.xml")
public class JTest5 {
    @Autowired
    private UserService userService;
    @Test
    public void test(){
        userService.accountMoney();
    }
}

