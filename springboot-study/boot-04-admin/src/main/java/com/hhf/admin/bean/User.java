package com.hhf.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("user")
public class User {
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String password;


    //user表要用
    private Long id;
    private String name;
    private Integer age;
    private String email;

}
