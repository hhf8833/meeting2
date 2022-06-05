package com.hhf.seckilldemo.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/**
 *
 * 将goods与秒杀goods进行关联
 * 继承goods的属性，添加没有的属性
 * @author HP
 */
@Data
public class GoodVo extends Goods{
    /**
     * 秒杀价格
     */
    private BigDecimal seckillPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    private Date endDate;
}
