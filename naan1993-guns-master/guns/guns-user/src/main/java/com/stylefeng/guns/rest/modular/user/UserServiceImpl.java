package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.MoocUserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocUserT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stylefeng.guns.api.user.UserAPI;

import java.util.Date;

/**
 * @author HP
 * 注意这里的service是对外暴力接口的实现
 * loadbalance = "roundrobin"   负载均衡策略为轮询
 */
@Component
@Service(interfaceClass = UserAPI.class,loadbalance = "roundrobin")
public class UserServiceImpl implements UserAPI{

    @Autowired
    private MoocUserTMapper moocUserTMapper;

    /**
     * 登录信息从后台传递到前台，通过jwt传到给客户端携带着jwt就是一个令牌，用缓存redis存入jwt 只要用这个jwt那么用户就不用再登录
     * {
     * “status” ： 0，
     * “data”：{
     * "randomKey":"nv0958",
     * "token":"eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJudjA5NTgiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTUzMjQ0MDY1MCwiaWF0IjoxNTMxODM1ODUwfQ.miIl1JXpb1ztkd-RHks1OPD6KQ53I-C4ESwhOywU0O7KDWu9SodHP0HMct0Di3BO2qtuc9EpVtSZcgTnnudrHA"
     * }
     * }
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public int login(String username, String password) {
        //根据登录账号获取数据库的数据
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(username);
        System.out.println("用户名："+moocUserT.getUserName());
//        QueryWrapper<MoocUserT> entityWrapper = new QueryWrapper<>();
//        entityWrapper.eq("user_name",username);
        MoocUserT result = moocUserTMapper.selectOne(moocUserT);
        System.out.println("查询用户结果："+result);
        //用获取的结果与加密后的密码做匹配
        if (result != null && result.getUuid() >0){
            String encryptPWD = MD5Util.encrypt(password);
            if (result.getUserPwd().equals(encryptPWD)){
                return result.getUuid();
            }
        }
        return 0 ;
    }

    @Override
    public boolean register(UserModel userModel) {

        //将注册信息实体转为数据实体[mooc_user_t]
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUserName(userModel.getUsername());

        moocUserT.setAddress(userModel.getAddress());
        moocUserT.setEmail(userModel.getEmail());
        moocUserT.setUserPhone(userModel.getPhone());

        //将密码MD5加密后存入数据库  正常的加密应该是使用【MD5+盐值】，最后将MD5加密的密码和盐值存入数据库中
        String encryptPWD = MD5Util.encrypt(userModel.getPassword());
        moocUserT.setUserPwd(encryptPWD);
        //将数据实体存入数据库
        Integer status = moocUserTMapper.insert(moocUserT);
        if (status > 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 检查当前用户名是否存在
     * @param username
     * @return
     */
    @Override
    public boolean checkUserName(String username) {
        //MybatisPlus 封装的一个查询条件构造器
        EntityWrapper<MoocUserT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_name",username);
        Integer count = moocUserTMapper.selectCount(entityWrapper);
        if (count !=null && count >0 ){
            return false;
        }else {
            return true;
        }

    }

    public UserInfoModel do2UserInfo(MoocUserT moocUserT){
        UserInfoModel userInfoModel = new UserInfoModel();
        userInfoModel.setUuid(moocUserT.getUuid());
        userInfoModel.setHeadAddress(moocUserT.getHeadUrl());
        userInfoModel.setPhone(moocUserT.getUserPhone());
        userInfoModel.setUpdateTime(moocUserT.getUpdateTime().getTime());
        userInfoModel.setEmail(moocUserT.getEmail());
        userInfoModel.setUsername(moocUserT.getUserName());
        userInfoModel.setNickname(moocUserT.getNickName());
        userInfoModel.setLifeState(""+moocUserT.getLifeState());
        userInfoModel.setBirthday(moocUserT.getBirthday());
        userInfoModel.setAddress(moocUserT.getAddress());
        userInfoModel.setSex(moocUserT.getUserSex());
        userInfoModel.setBeginTime(moocUserT.getBeginTime().getTime());
        userInfoModel.setBiography(moocUserT.getBiography());
        return userInfoModel;
    }
    @Override
    public UserInfoModel getUserInfo(int uuid) {
        //根据主键查询用户信息(MoocUserT)
        MoocUserT moocUserT = moocUserTMapper.selectById(uuid);
        //将MoocUserT转换为UserInfoModel
        UserInfoModel userInfoModel = do2UserInfo(moocUserT);
        //返回UserInfoModel
        return userInfoModel;
    }

    private Date do2Date(long time){
        Date date = new Date(time);
        return date;
    }
    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        //将传入的数据转换为moocUserT
        MoocUserT moocUserT = new MoocUserT();
        moocUserT.setUuid(userInfoModel.getUuid());
        moocUserT.setNickName(userInfoModel.getNickname());
        moocUserT.setLifeState(Integer.parseInt(userInfoModel.getLifeState()));
        moocUserT.setBirthday(userInfoModel.getBirthday());
        moocUserT.setBiography(userInfoModel.getBiography());
        moocUserT.setBeginTime(null);
        moocUserT.setHeadUrl(userInfoModel.getHeadAddress());
        moocUserT.setEmail(userInfoModel.getEmail());
        moocUserT.setAddress(userInfoModel.getAddress());
        moocUserT.setUserPhone(userInfoModel.getPhone());
        moocUserT.setUserSex(userInfoModel.getSex());
        moocUserT.setUpdateTime(do2Date(System.currentTimeMillis()));

        Integer isSuccess = moocUserTMapper.updateById(moocUserT);
        if (isSuccess>0){
            //将用户信息拿出来返回给前端
            UserInfoModel userInfo = getUserInfo(moocUserT.getUuid());
            return userInfo;
        }else {
            //失败了就返回没改之前的
            return userInfoModel;
        }

    }
}
