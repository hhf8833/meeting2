package com.stylefeng.guns.api.order.vo;

import lombok.Data;

import java.io.Serializable;
/**
 * @author HP
 * "orderId":"18392981493",
 * 		"filmName":"基于SpringBoot 十分钟搞定后台管理平台",
 * 		"fieldTime":"今天 9月8号 11:50",
 * 		"cinemaName":"万达影城(顺义金街店)",
 * 		"seatsName":"1排3座 1排4座 2排4座",
 * 		"orderPrice":"120",
 * 		"orderTimestamp":"1589754126"
 */
@Data
public class OrderVO implements Serializable {

    private String orderId;
    private String filmName;
    private String fieldTime;
    private String cinemaName;
    private String seatsName;
    private String orderPrice;
    private String orderTimestamp;
    private String orderStatus;

}
