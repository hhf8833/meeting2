package com.hhf.admin;

import com.hhf.admin.bean.User;
import com.hhf.admin.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class Boot04AdminApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        User user =userMapper.selectById(1l);
        log.info("用户信息{}",user);
    }
}
