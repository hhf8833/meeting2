package com.hhf.seckilldemo.service;

import com.hhf.seckilldemo.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhf.seckilldemo.vo.LoginVo;
import com.hhf.seckilldemo.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hhf
 * @since 2022-02-15
 */
public interface IUserService extends IService<User> {

    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据cookie获取用户
     * @param userTicket
     * @return
     */
    User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);

    RespBean updatePassword(String userTicket , String password,HttpServletRequest request, HttpServletResponse response);
}
