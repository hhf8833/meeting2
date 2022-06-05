package com.stylefeng.guns.api.user;

import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;

public interface UserAPI {
    /**
     * 登录信息从后台传递到前台，通过jwt传到给客户端携带着jwt就是一个令牌，用缓存redis存入jwt 只要用这个jwt那么用户就不用再登录
     * {
     * “status” ： 0，
     *   “data”：{
     * 	"randomKey":"nv0958",
     * "token":"eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJudjA5NTgiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTUzMjQ0MDY1MCwiaWF0IjoxNTMxODM1ODUwfQ.miIl1JXpb1ztkd-RHks1OPD6KQ53I-C4ESwhOywU0O7KDWu9SodHP0HMct0Di3BO2qtuc9EpVtSZcgTnnudrHA"
     *        }
     * }
     * @param username
     * @param password
     * @return
     */
    int login(String username,String password);

    boolean register(UserModel userModel);

    boolean checkUserName(String username);

    UserInfoModel getUserInfo(int uuid);

    UserInfoModel updateUserInfo(UserInfoModel userInfoModel);
}
