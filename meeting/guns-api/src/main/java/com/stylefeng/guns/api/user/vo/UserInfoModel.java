package com.stylefeng.guns.api.user.vo;

import lombok.Data;

import java.awt.dnd.DropTarget;
import java.io.Serializable;
import java.util.Date;

/**
 * @author HP
 * {
 *     "status": 0,
 *     "data": {
 *         "uuid": 12,
 *         "username": "aaa",
 *         “nickname”: ”咫尺天涯”,
 * "email": "aaa@163.com",
 *         "phone": null,
 * 		“sex” : 0, [0-男，1-女]，
 * 		“birthday”：“2018-12-12”，
 * 		“lifeState” ：0, [0-单身，1-热恋中，2-已婚，3-为人父母]，
 * 		“biography” ：“没有合适的伞，我宁可淋着雨”,
 *         “address”:”北京市东城区中南海12号楼主席办公室”，
 * 		“headAddress” : “http://img.meetingshop.cn/12324123.png“
 *         "createTime": 1479048325000,
 *         "updateTime": 1479048325000
 *     }
 * }
 */
@Data
public class UserInfoModel implements Serializable {
        private Integer uuid;
        private String username;
        private String nickname;
        private String email ;
        private String phone;
        private int sex ;
        private String birthday;
        private String lifeState;
        private String biography;
        private String address;
        private String headAddress;
        private long beginTime;
        private long updateTime;




}
