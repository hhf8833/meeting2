package com.stylefeng.guns.api.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 因为是负责在各个服务里面传输得所以要加上序列化
 * 用于注册
 */
@Data
public class UserModel implements Serializable {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

}
