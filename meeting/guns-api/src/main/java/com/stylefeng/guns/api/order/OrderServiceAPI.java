package com.stylefeng.guns.api.order;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.order.vo.OrderVO;

import java.util.List;

public interface OrderServiceAPI {

    //验证售出的票是否为真，即是否有这个座位
    boolean isTrueSeats(String filedId,String seats);

    //已经销售的座位里，有没有这个座位
    boolean isNotSoldSeats(String filedId,String seats);

    //创建订单信息
    OrderVO saveOrderInfo(Integer fieldId,String soldSeats, String seatsName,Integer userId);

    //根据fieldID获取所有已经销售的座位
    String getSoldSeatsByFildId(Integer fieldId);

    //使用当前登陆人已经购买的订单
    Page<OrderVO> getOrderByUserId(Integer userId, Page<OrderVO> page);



    //根据订单编号获取订单信息
    OrderVO getOrderInfoById(String orderId);

    boolean paySuccess(String orderId);
    boolean payFail(String orderId);
}
