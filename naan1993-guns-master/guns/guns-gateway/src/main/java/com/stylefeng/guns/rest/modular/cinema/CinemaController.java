package com.stylefeng.guns.rest.modular.cinema;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.cinema.CinemaServiceAPI;
import com.stylefeng.guns.api.cinema.vo.*;
import com.stylefeng.guns.api.order.OrderServiceAPI;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaConditionResponseVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFieldResponseVO;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaFieldsResponseVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cinema/")
public class CinemaController {
    //当前客户端接口是可以连接10个服务端的
    @Reference(interfaceClass = CinemaServiceAPI.class,check = false,cache = "lru",connections = 10)
    private CinemaServiceAPI cinemaServiceAPI;

    @Reference(interfaceClass = OrderServiceAPI.class,check = false,group = "default")
    private OrderServiceAPI orderServiceAPI;
    private static  String imgPre= "http://img.meetingshop.cn/";
    /**
     *  1.查询影院列表
     * @param cinemaQueryVO
     * @return
     */
    @GetMapping("getCinemas")
    public ResponseVo getCinemas(CinemaQueryVO cinemaQueryVO){
        try{
            //按照五个条件进行筛选
            Page<CinemaVO> cinemas = cinemaServiceAPI.getCinemas(cinemaQueryVO);
            //判断是否有满足条件的影院
            if (cinemas.getRecords() ==null ||cinemas.getRecords().size() ==0){
                return ResponseVo.success("没有影院可查");
            }else {
                return ResponseVo.success(cinemas.getCurrent(),(int)cinemas.getPages(),"",cinemas.getRecords());
            }
        }catch (Exception e){
            log.error("获取影院列表异常",e);
            return ResponseVo.serviceFail("查询影院列表失败");
        }


        //出现异常解决
    }

    /**
     *          1、热点数据 -> 放缓存
     *           2、banner
     * 2、	获取影院列表查询条件
     * 影院编号
     * 影厅类型
     * 行政区编号
     * @param cinemaQueryVO
     * @return
     */
    @GetMapping("getCondition")
    public ResponseVo getCondition(CinemaQueryVO cinemaQueryVO){
        try{
            // 获取三个集合，然后封装成一个对象返回即可
            List<BrandVO> brands = cinemaServiceAPI.getBrands(cinemaQueryVO.getBrandId());
            List<AreaVO> areas = cinemaServiceAPI.getAreas(cinemaQueryVO.getDistrictId());
            List<HallTypeVO> hallTypes = cinemaServiceAPI.getHallTypes(cinemaQueryVO.getHallType());

            CinemaConditionResponseVO cinemaConditionResponseVO = new CinemaConditionResponseVO();
            cinemaConditionResponseVO.setAreaList(areas);
            cinemaConditionResponseVO.setBrandList(brands);
            cinemaConditionResponseVO.setHalltypeList(hallTypes);

            return ResponseVo.success(cinemaConditionResponseVO);
        }catch (Exception e) {
            log.error("获取条件列表失败", e);
            return ResponseVo.serviceFail("获取影院查询条件失败");
        }
    }

    /**
     * 3、	获取播放场次接口
     * @param cinemaId
     * @return
     */
    @RequestMapping("getFields")
    public ResponseVo getFields(Integer cinemaId){
        try {
            //获取影院编号
            CinemaInfoVO cinemaInfoById = cinemaServiceAPI.getCinemaInfoById(cinemaId);
            //根据影院编号,获取所有电影的信息和对应的放映场次信息，
            List<FilmInfoVO> filmInfoByCinemaId = cinemaServiceAPI.getFilmInfoByCinemaId(cinemaId);


            CinemaFieldsResponseVO cinemaFieldResponseVO = new CinemaFieldsResponseVO();
            cinemaFieldResponseVO.setCinemaInfo(cinemaInfoById);
            cinemaFieldResponseVO.setFilmList(filmInfoByCinemaId);
            return ResponseVo.success(imgPre,cinemaFieldResponseVO);
        }catch (Exception e ){
            log.error("获取播放场次失败", e);
            return ResponseVo.serviceFail("获取播放场次失败");
        }

    }

    /**
     * 4、	获取场次详细信息接口
     * @param cinemaId
     * @return
     */
    @PostMapping("getFieldInfo")
    public ResponseVo getFields(Integer cinemaId,Integer fieldId){
        try{

            CinemaInfoVO cinemaInfoById = cinemaServiceAPI.getCinemaInfoById(cinemaId);
            FilmInfoVO filmInfoByFieldId = cinemaServiceAPI.getFilmInfoByFieldId(fieldId);
            HallInfoVO filmFieldInfo = cinemaServiceAPI.getFilmFieldInfo(fieldId);

            // 造几个销售的假数据，后续会对接订单接口
            // filmFieldInfo.setSoldSeats("1,2,3,4");
            filmFieldInfo.setSoldSeats(orderServiceAPI.getSoldSeatsByFildId(fieldId));


            CinemaFieldResponseVO cinemaFieldResponseVO = new CinemaFieldResponseVO();
            cinemaFieldResponseVO.setCinemaInfo(cinemaInfoById);
            cinemaFieldResponseVO.setFilmInfo(filmInfoByFieldId);
            cinemaFieldResponseVO.setHallInfo(filmFieldInfo);

            return ResponseVo.success(imgPre,cinemaFieldResponseVO);
        }catch (Exception e){
            log.error("获取选座信息失败",e);
            return ResponseVo.serviceFail("获取选座信息失败");
        }

    }
}
