package com.hhf.seckilldemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hhf.seckilldemo.mapper.GoodsMapper;
import com.hhf.seckilldemo.pojo.GoodVo;
import com.hhf.seckilldemo.pojo.Goods;
import com.hhf.seckilldemo.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hhf
 * @since 2022-03-03
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<GoodVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }

    @Override
    public GoodVo findGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }
}
