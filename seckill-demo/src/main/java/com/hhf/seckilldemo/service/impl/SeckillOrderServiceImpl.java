package com.hhf.seckilldemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhf.seckilldemo.mapper.OrderMapper;
import com.hhf.seckilldemo.mapper.SeckillOrderMapper;
import com.hhf.seckilldemo.pojo.*;
import com.hhf.seckilldemo.service.ISeckillGoodsService;
import com.hhf.seckilldemo.service.ISeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hhf
 * @since 2022-03-03
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {


}
