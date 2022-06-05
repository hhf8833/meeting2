package com.stylefeng.guns.rest.modular.auth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.core.constant.RedisConstants;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.rest.common.RedisUtils;
import com.stylefeng.guns.rest.common.exception.BizExceptionEnum;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import com.stylefeng.guns.rest.modular.auth.validator.IReqValidator;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 请求验证的
 * 当用户第一次登录的时候，输入账号密码url为/auth?userName=hhf&password=hhf，Authfilter在遇到/auth的时候不会对其拦截
 *  而是直接走到controller中进行判断账号密码是否正确，正确了则发出token供下次使用
 *  当有了token的时候，用户是不会在进入rul为/auth的了，每次访问的时候会把token带上，这时候authfilter就派上用场了
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@Slf4j
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

/*    @Resource(name = "simpleValidator")
    private IReqValidator reqValidator;*/

    @Reference(interfaceClass = UserAPI.class,check = false)
    private UserAPI userAPI;

    @Autowired
    private RedisUtils redisUtils;
    @RequestMapping(value = "${jwt.auth-path}")
    public ResponseVo createAuthenticationToken(AuthRequest authRequest) {
        /***
         * 能请求到AuthContoroller，说明两种情况：
         *
         * 你没有携带token
         * 你token过期了
         */

        //String mes =userAPI.login(authRequest.getUserName(),authRequest.getPassword());
       // System.out.println("client"+mes);

        //boolean validate = reqValidator.validate(authRequest);
        //去掉guns自身的密码验证机制，用自己的
        boolean validate=true;
        int userId = userAPI.login(authRequest.getUserName(), authRequest.getPassword());
        System.out.println("username="+authRequest.getUserName());
        System.out.println("getPassword="+authRequest.getPassword());
        if (userId ==0){
            validate =false;
        }
       //System.out.println("userId 为"+userId);
        if (validate) {
            String userID = String.valueOf(userId);
            if (redisUtils.hasKey(userID)){
                //如果存在，只有两种情况，第一种是你从其他地方登录了，第二种别人登录的，或者是你过期了但是系统还保存着你的token缓存
                redisUtils.del(userID);
            }
            final String randomKey = jwtTokenUtil.getRandomKey();
            //利用userID作为token
            final String token = jwtTokenUtil.generateToken(""+userId, randomKey);
            boolean userToRedis = redisUtils.set(userID, token, RedisConstants.TOKEN_EXPIRE.getTime());
            if (!userToRedis){
                log.error("无法在redis中为用户创建缓存");
                return ResponseVo.serviceFail("无法在redis中为用户创建缓存");
            }
            return ResponseVo.success(new AuthResponse(token, randomKey));
        } else {
            return ResponseVo.serviceFail("用户名或者密码错误");
        }
    }
}
