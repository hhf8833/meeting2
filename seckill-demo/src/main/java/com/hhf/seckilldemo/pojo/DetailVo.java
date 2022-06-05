package com.hhf.seckilldemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.jws.soap.SOAPBinding;

/**
 * 详情返回对象，用于detail缓存
 * @author HP
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailVo {

    private User user;
    private GoodVo goodVo;
    private int seckillStatus;
    private int remainSeconds;
}
