package com.stylefeng.guns.rest.modular;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserAPI;
import org.springframework.stereotype.Component;

@Component
public class Client {
    @Reference(interfaceClass = UserAPI.class)
    private UserAPI userAPI;

    public void run() {
/*        String mes =userAPI.login("admin","password");
        System.out.println("client"+mes);*/
    }
}
