package com.hhf.seckilldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhf.seckilldemo.pojo.GoodVo;
import com.hhf.seckilldemo.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hhf
 * @since 2022-03-03
 */
//@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodVo> findGoodsVo();

    GoodVo findGoodsVoByGoodsId(Long goodsId);
}
