package com.stylefeng.guns.rest.modular.order.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.cinema.CinemaServiceAPI;
import com.stylefeng.guns.api.cinema.vo.FilmInfoVO;
import com.stylefeng.guns.api.cinema.vo.OrderQueryVO;
import com.stylefeng.guns.api.order.OrderServiceAPI;
import com.stylefeng.guns.api.order.vo.OrderVO;
import com.stylefeng.guns.core.util.UUIDUtil;
import com.stylefeng.guns.rest.common.persistence.dao.MoocOrder2017TMapper;
import com.stylefeng.guns.rest.common.persistence.dao.MoocOrderTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrder2017T;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import com.stylefeng.guns.rest.common.util.FTPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Service(interfaceClass = OrderServiceAPI.class,group = "order2017")
public class OrderServiceImpl2017 implements OrderServiceAPI {

    @Autowired
    private MoocOrder2017TMapper moocOrder2017TMapper;

    @Autowired
    private FTPUtil ftpUtil;

    @Reference(interfaceClass = CinemaServiceAPI.class,check = false)
    private CinemaServiceAPI cinemaServiceAPI;

    //验证售出的票是否为真，即是否有这个座位
    @Override
    public boolean isTrueSeats(String fieldId,  String seats) {

        //根据对应的fieldId找到对应的座位位置图  缓存中取
        String seatsPath = moocOrder2017TMapper.getSeatsByFieldId(fieldId);
        System.out.println("座位位置图是："+seatsPath);
        //读位置图，判断seats是否为真
        String fileStrByAddress = ftpUtil.getFileStrByAddress(seatsPath);
        //将其转为json
        JSONObject jsonObject = JSONObject.parseObject(fileStrByAddress);
        String ids = jsonObject.get("ids").toString();

        String[] idArrs = ids.split(",");
        String[] seatsArrs = seats.split(",");
        int isTrue =0;
        for (String seat : seatsArrs) {
            for (String id : idArrs) {
                if (seat.equals(id)) {
                    isTrue++;
                }
            }
        }
        if (isTrue == seatsArrs.length){
            return true;
        }else {
            return false;

        }
    }
    //已经销售的座位里，有没有这个座位
    @Override
    public boolean isNotSoldSeats(String filedId, String seats) {
        EntityWrapper entityWrapper = new EntityWrapper();
        //找到对应场次的所有订单
        entityWrapper.eq("field_id",filedId);
        List<MoocOrder2017T> list = moocOrder2017TMapper.selectList(entityWrapper);
        String[] willBuySeats = seats.split(",");
        for (MoocOrder2017T order : list) {
            String[] soldedSeats = order.getSeatsIds().split(",");
            for (String soldedSeat : soldedSeats) {
                for (String willBuySeat : willBuySeats) {
                    if (soldedSeat.equals(willBuySeat)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //创建订单信息
    @Override
    public OrderVO saveOrderInfo(Integer fieldId, String soldSeats, String seatsName, Integer userId) {
        //订单编号
        String uuid = UUIDUtil.genUuid();
        //影片信息
        FilmInfoVO filmInfoByFieldId = cinemaServiceAPI.getFilmInfoByFieldId(fieldId);
        Integer filmId = Integer.parseInt(filmInfoByFieldId.getFilmId());
        //影院信息，价格
        OrderQueryVO orderQueryVO = cinemaServiceAPI.getOrderNeeds(fieldId);
        Integer cinemaId = Integer.parseInt(orderQueryVO.getCinemaId());
        double filmPrice = Double.parseDouble(orderQueryVO.getFilmPrice());

        //求订单总金额
        int solds = soldSeats.split(",").length;
        double totalPrice = getTotalPrice(solds, filmPrice);
        MoocOrder2017T moocOrderT = new MoocOrder2017T();
        moocOrderT.setUuid(uuid);
        moocOrderT.setSeatsName(seatsName);
        moocOrderT.setSeatsIds(soldSeats);
        moocOrderT.setOrderUser(userId);
        moocOrderT.setOrderPrice(totalPrice);
        moocOrderT.setFilmPrice(filmPrice);
        moocOrderT.setFilmId(filmId);
        moocOrderT.setFieldId(fieldId);
        moocOrderT.setCinemaId(cinemaId);
        Integer insert = moocOrder2017TMapper.insert(moocOrderT);
        if (insert>0){
            //返回查询结果
            /*
            "orderId":"18392981493",
            "filmName":"基于SpringBoot 十分钟搞定后台管理平台",
            "fieldTime":"今天 9月8号 11:50",
            "cinemaName":"万达影城(顺义金街店)",
            "seatsName":"1排3座 1排4座 2排4座",
            "orderPrice":"120",
            "orderTimestamp":"1589754126"
             */
            OrderVO orderVO = moocOrder2017TMapper.getOrderInfoById(uuid);
            if(orderVO == null || orderVO.getOrderId() ==null){
                log.error("订单信息查询失败，订单编号为{}",uuid);
                return null;
            }else {
                return orderVO;
            }

        }else {
            //插入失败
            log.error("插入订单失败");
            return null;
        }

    }

    private double getTotalPrice(int solds, double filmPrice) {
        BigDecimal soldsDeci = new BigDecimal(solds);
        BigDecimal filmPriceDeci = new BigDecimal(filmPrice);
        BigDecimal multiply = soldsDeci.multiply(filmPriceDeci);
        //四舍五入，保留两位
        BigDecimal res = multiply.setScale(2, RoundingMode.HALF_UP);
        return res.doubleValue();
    }


    //根据fieldID获取所有已经销售的座位
    @Override
    public String getSoldSeatsByFildId(Integer fieldId) {
        if (fieldId ==null){
            log.error("查询已售座位错误，未传入任何场次");
            return "";
        }else {
            //从缓存中获取得到的已卖座位，并不需要保证真正一致  因为只是读
            String soldSeatsByFildId = moocOrder2017TMapper.getSoldSeatsByFildId(fieldId);
            return soldSeatsByFildId;
        }
    }

    @Override
    public Page<OrderVO> getOrderByUserId(Integer userId, Page<OrderVO> page) {

        Page<OrderVO> res = new Page<>();
        if (userId ==null){
            log.error("订单业务失败，用户编号为传入");
            return null;
        }else {
            List<OrderVO> orderInfoByUserId = moocOrder2017TMapper.getOrderInfoByUserId(userId,page);
            if (orderInfoByUserId ==null && orderInfoByUserId.size() ==0){
                res.setTotal(0);
                res.setRecords(new ArrayList<>());
                return res;
            }else {
                //获取订单总数
                EntityWrapper<MoocOrder2017T> entityWrapper = new EntityWrapper<>();
                entityWrapper.eq("order_user",userId);
                Integer count = moocOrder2017TMapper.selectCount(entityWrapper);

                //将结果放入page
                res.setTotal(count);
                res.setRecords(orderInfoByUserId);
                return res;
            }
        }
    }

    @Override
    public OrderVO getOrderInfoById(String orderId) {
        OrderVO orderInfoById = moocOrder2017TMapper.getOrderInfoById(orderId);
        return orderInfoById;
    }
    @Override
    public boolean paySuccess(String orderId) {
        MoocOrder2017T moocOrderT = new MoocOrder2017T();
        moocOrderT.setUuid(orderId);
        //1成功，2失败
        moocOrderT.setOrderStatus(1);
        Integer update = moocOrder2017TMapper.updateById(moocOrderT);
        if (update>=1){
            return true;
        }
        return false;
    }

    @Override
    public boolean payFail(String orderId) {
        MoocOrder2017T moocOrderT = new MoocOrder2017T();
        moocOrderT.setUuid(orderId);
        //1成功，2失败
        moocOrderT.setOrderStatus(2);
        Integer update = moocOrder2017TMapper.updateById(moocOrderT);
        if (update>=1){
            return true;
        }
        return false;
    }
}
