package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;
import com.stylefeng.guns.rest.common.CurrentUser;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Reference(interfaceClass = UserAPI.class,check = false)
    private UserAPI userAPI;

    @PostMapping("register")
    public ResponseVo register(UserModel userModel){
        if (userModel.getUsername() ==null || userModel.getUsername().trim().length() ==0){
            return ResponseVo.serviceFail("用户名不能为空");
        }
        if(userModel.getPassword() == null || userModel.getPassword().trim().length()==0){
            return ResponseVo.serviceFail("密码不能为空");
        }
        boolean register = userAPI.register(userModel);
        if (register){
            return ResponseVo.success("注册成功");
        }else {
            return ResponseVo.serviceFail("注册失败");
        }
    }

    @PostMapping("check")
    public ResponseVo check(String username){
        if (username!=null && username.trim().length()>0){
            //true 说明用户名不存在，false说明存在
            boolean notExist = userAPI.checkUserName(username);
            if (notExist){
                return ResponseVo.success("用户名不存在");
            }else {
                return ResponseVo.serviceFail("用户名已存在");
            }
        }else {
            return ResponseVo.serviceFail("用户名不能为空");
        }
    }

    @GetMapping("loginout")
    public ResponseVo loginout(String username){
        /*
            应用：
                1、前端存储JWT 【七天】 ： JWT的刷新
                2、服务器端会存储活动用户信息【30分钟】
                3、JWT里的userId为key，查找活跃用户
            退出：
                1、前端删除掉JWT
                2、后端服务器删除活跃用户缓存
            现状：这次没有用缓存那些
                1、前端删除掉JWT
         */
        return ResponseVo.success("用户成功退出");
    }

    /**
     * 这里用了threadlocal里面存储的用户信息
     * @return
     */
    @GetMapping("getUserInfo")
    public ResponseVo getUserInfo(){
        String userId = CurrentUser.getCurrentUser();
        if (userId !=null && userId.trim().length() >0){
            int uuid = Integer.parseInt(userId);
            UserInfoModel userInfo = userAPI.getUserInfo(uuid);
            if (userInfo !=null){
                return ResponseVo.success(userInfo);
            }else {
                return ResponseVo.appFail("用户信息查询失败");
            }
        }else {
            return ResponseVo.serviceFail("用户信息过期");
        }
    }

    @PostMapping("updateUserInfo")
    public ResponseVo updateUserInfo(UserInfoModel userInfoModel){
        String userId = CurrentUser.getCurrentUser();
        if (userId !=null && userId.trim().length() >0){
            int uuid = Integer.parseInt(userId);
            //判断传来的id和当前系统保存的id是否一致
            if (userInfoModel.getUuid() !=uuid){
                return ResponseVo.serviceFail("个人信息不匹配求重试");
            }

            UserInfoModel userInfo = userAPI.updateUserInfo(userInfoModel);
            if (userInfo !=null){
                return ResponseVo.success(userId);
            }else {
                return ResponseVo.appFail("用户信息修改失败");
            }
        }else {
            return ResponseVo.serviceFail("用户未登录");
        }
    }
}
