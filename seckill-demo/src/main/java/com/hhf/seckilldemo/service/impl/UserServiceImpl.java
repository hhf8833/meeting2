package com.hhf.seckilldemo.service.impl;

import com.hhf.seckilldemo.exception.GlobalException;
import com.hhf.seckilldemo.pojo.User;
import com.hhf.seckilldemo.mapper.UserMapper;
import com.hhf.seckilldemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhf.seckilldemo.utils.CookieUtil;
import com.hhf.seckilldemo.utils.MD5Util;
import com.hhf.seckilldemo.utils.UUIDUtil;
import com.hhf.seckilldemo.vo.LoginVo;
import com.hhf.seckilldemo.vo.RespBean;
import com.hhf.seckilldemo.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hhf
 * @since 2022-02-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
/*  这里的判断使用的是自定义注解参数校验
      if (!StringUtils.hasLength(mobile) || !StringUtils.hasLength(password)){
            return  RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if (!ValidatorUtil.isMobile(mobile)){
            return RespBean.error(RespBeanEnum.MOBILE_FORMAT_ERROR);
        }*/
        User user = userMapper.selectById(mobile);
        if (user== null){
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
            throw  new GlobalException(RespBeanEnum.MOBILE_ERROR);
        }
        if (!MD5Util.firstPwdToDBpwd(password,user.getSalt()).equals(user.getPassword())){
            //下面两个方法虽然返回的对象不一样一个是RespBean一个是RespBeanEnum，但是因为前端得到对象时，
            // 取的都是对象的属性code或message，而两者均有该属性，所以不影响
        //  return RespBean.error(RespBeanEnum.LOGIN_ERROR);
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
        //生成cookie
        String ticket = UUIDUtil.uuid();
        //将用户信息存入redis中
        redisTemplate.opsForValue().set("user:"+ticket,user);
        
//        request.getSession().setAttribute(ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success(ticket);
    }

    /**
     * 根据cookie获取用户
     *
     * @param userTicket
     * @param request
     * @param response
     * @return
     */
    @Override
    public User getUserByCookie(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if (!StringUtils.hasLength(userTicket)){
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + userTicket);

        System.out.println("根据cookie再redis中得到user"+user);
        //CookieUtil.setCookie(request,response,"userTicket",userTicket);
        return user;
    }

    /**
     * 场景   redis中的用户是不更新的，如果用户更新密码之后，redis对应的用户信息是不变的 ，所以要把redis里面的也变了
     * @param userTicket
     * @param password
     * @param request
     * @param response
     * @return
     */
    @Override
    public RespBean updatePassword(String userTicket,  String password,HttpServletRequest request, HttpServletResponse response) {
        User user = getUserByCookie(userTicket, request, response);
        if (user == null){
            throw new GlobalException(RespBeanEnum.MOBILE_ERROR);
        }
        user.setPassword(MD5Util.inputPwdToDBPwd(password,user.getSalt()));
        int result = userMapper.updateById(user);
        if (result ==1){

            redisTemplate.delete("user:"+userTicket);
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_FAIL);
    }


}
