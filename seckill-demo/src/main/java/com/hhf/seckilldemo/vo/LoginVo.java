package com.hhf.seckilldemo.vo;

import com.hhf.seckilldemo.validator.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class LoginVo {
    @IsMobile
    @NotNull
    private String mobile;
    @NotNull
    @Length(min = 8)
    private String password;
}
