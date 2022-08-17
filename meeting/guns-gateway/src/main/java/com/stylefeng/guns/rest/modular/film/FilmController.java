package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.stylefeng.guns.api.film.FilmAsyncServiceApi;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.rest.modular.film.vo.FilmConditionVO;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVO;
import com.stylefeng.guns.rest.modular.film.vo.FilmRequestVO;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/film/")
public class FilmController {

    @Reference(interfaceClass = FilmServiceAPI.class,check = false)
    private FilmServiceAPI filmServiceAPI ;

    @Reference(interfaceClass = FilmAsyncServiceApi.class,async = true,check = false)
    private FilmAsyncServiceApi filmAsyncServiceApi ;

    private static  String imgPre= "http://img.meetingshop.cn/";
    /**
     * 获取首页
     *         API网关：
     *             1、功能聚合【API聚合】
     *             好处：
     *                 1、六个接口，一次请求，同一时刻节省了五次HTTP请求
     *                 2、同一个接口对外暴漏，降低了前后端分离开发的难度和复杂度
     *             坏处：
     *                 1、一次获取数据过多，容易出现问题
     * @return
     */
    @GetMapping("getIndex")
    public ResponseVo<FilmIndexVO> getIndex(){
        FilmIndexVO filmIndexVO = new FilmIndexVO();
        // 获取banner信息
        filmIndexVO.setBanners(filmServiceAPI.getBanners());
        // 获取正在热映的电影
        filmIndexVO.setHotFilms(filmServiceAPI.getHotFilms(true,8,1,1,99,99,99));
        // 即将上映的电影
        filmIndexVO.setSoonFilms(filmServiceAPI.getSoonFilms(true,8,1,1,99,99,99));
        // 票房排行榜
        filmIndexVO.setBoxRanking(filmServiceAPI.getBoxRanking());
        // 获取受欢迎的榜单
        filmIndexVO.setExpectRanking(filmServiceAPI.getExpectRanking());
        // 获取前一百
        filmIndexVO.setTop100(filmServiceAPI.getTop());

        return ResponseVo.success(imgPre,filmIndexVO);
    }

    /**
     * 影片条件列表查询接口  默认值99 为所有的数据
     * @param catId
     * @param sourceId
     * @param yearId
     * @return
     */
    @GetMapping("getConditionList")
    public ResponseVo<FilmConditionVO> getConditionList(@RequestParam(name = "catId",required = false,defaultValue = "99")String catId,
                                                        @RequestParam(name = "sourceId",required = false,defaultValue = "99")String sourceId,
                                                        @RequestParam(name = "yearId",required = false,defaultValue = "99")String yearId){

        FilmConditionVO filmConditionVO = new FilmConditionVO();
        // 标识位
        boolean flag = false;
        //类型集合
        List<CatVO> cats = filmServiceAPI.getCats();
        List<CatVO> catResult = new ArrayList<>();
        CatVO cat99 = null;
        for(CatVO catVO : cats){
            // 判断集合是否存在catId，如果存在，则将对应的实体变成active状态
            // 6
            // 1,2,3,99,4,5 ->
            /*
                优化：【理论上】
                    1、数据层查询按Id进行排序【有序集合 -> 有序数组】
                    2、通过二分法查找
             */
            //将这个99这个单独拿出来
            if("99".equals(catVO.getCatId())){
                cat99 = catVO;
                continue;
            }
            if(catVO.getCatId().equals(catId)){
                flag = true;
                catVO.setActive(true);
            }else{
                catVO.setActive(false);
            }
            catResult.add(catVO);
        }
        // 如果不存在，则默认将全部99这一项变为Active状态
        if(!flag){
            cat99.setActive(true);
            catResult.add(cat99);
        }else{
            cat99.setActive(false);
            catResult.add(cat99);
        }


    // 片源集合
        flag=false;
        List<SourceVO> sources = filmServiceAPI.getSources();
        List<SourceVO> sourceResult = new ArrayList<>();
        SourceVO sourceVO = null;
        for(SourceVO source : sources){
            if(source.getSourceId().equals("99")){
                sourceVO = source;
                continue;
            }
            if(source.getSourceId().equals(sourceId)){
                flag = true;
                source.setActive(true);
            }else{
                source.setActive(false);
            }
            sourceResult.add(source);
        }
        // 如果不存在，则默认将全部变为Active状态
        if(!flag){
            sourceVO.setActive(true);
            sourceResult.add(sourceVO);
        }else{
            sourceVO.setActive(false);
            sourceResult.add(sourceVO);
        }

        // 年代集合
        flag=false;
        List<YearVO> years = filmServiceAPI.getYears();
        List<YearVO> yearResult = new ArrayList<>();
        YearVO yearVO = null;
        for(YearVO year : years){
            if(year.getYearId().equals("99")){
                yearVO = year;
                continue;
            }
            if(year.getYearId().equals(yearId)){
                flag = true;
                year.setActive(true);
            }else{
                year.setActive(false);
            }
            yearResult.add(year);
        }
        // 如果不存在，则默认将全部变为Active状态
        if(!flag){
            yearVO.setActive(true);
            yearResult.add(yearVO);
        }else{
            yearVO.setActive(false);
            yearResult.add(yearVO);
        }

        filmConditionVO.setCatInfo(catResult);
        filmConditionVO.setSourceInfo(sourceResult);
        filmConditionVO.setYearInfo(yearResult);
        return ResponseVo.success(filmConditionVO);
    }

    @GetMapping("getFilms")
    public ResponseVo getFilms(FilmRequestVO filmRequestVO){
        //String img_pre = "http://img.meetingshop.cn/";

        FilmVO filmVO = null;
        // 根据showType判断影片查询类型
        switch (filmRequestVO.getShowType()){
            case 1 :
                filmVO = filmServiceAPI.getHotFilms(
                        false,filmRequestVO.getPageSize(),filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(),filmRequestVO.getSourceId(),filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
            case 2 :
                filmVO = filmServiceAPI.getSoonFilms(
                        false,filmRequestVO.getPageSize(),filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(),filmRequestVO.getSourceId(),filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
            case 3 :
                filmVO = filmServiceAPI.getClassicFilms(
                        filmRequestVO.getPageSize(),filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(),filmRequestVO.getSourceId(),
                        filmRequestVO.getYearId(), filmRequestVO.getCatId());
                break;
            default:
                filmVO = filmServiceAPI.getHotFilms(
                        false,filmRequestVO.getPageSize(),filmRequestVO.getNowPage(),
                        filmRequestVO.getSortId(),filmRequestVO.getSourceId(),filmRequestVO.getYearId(),
                        filmRequestVO.getCatId());
                break;
        }
        // 根据sortId排序
        // 添加各种条件查询
        // 判断当前是第几页

        return ResponseVo.success(
                filmVO.getNowPage(),filmVO.getTotalPage(),
                imgPre,filmVO.getFilmInfo());

    }

    /**
     * searchType : ‘0表示按照编号查找，1表示按照名称查找’
     * @param searchParam
     * @param searchType
     * @return
     */
    @GetMapping("films/{searchParam}")
    public ResponseVo films(@PathVariable("searchParam")String searchParam,
                            int searchType) throws ExecutionException, InterruptedException {

        // 根据searchType，判断查询类型
        FilmDetailVO filmDetail = filmServiceAPI.getFilmDetail(searchType, searchParam);

        if(filmDetail==null){
            return ResponseVo.serviceFail("没有可查询的影片");
        }else if(filmDetail.getFilmId()==null || filmDetail.getFilmId().trim().length()==0){
            return ResponseVo.serviceFail("没有可查询的影片");
        }

        String filmId = filmDetail.getFilmId();

        //不同的查询类型，传入的条件会有不同

        //查询详细信息  dubbo异步获取

        //FilmDescVO filmDesc = filmAsyncServiceApi.getFilmDesc(filmId);
        filmAsyncServiceApi.getFilmDesc(filmId);
        Future<FilmDescVO> filmDescVOFuture = RpcContext.getContext().getFuture();
        // 获取图片信息
        filmAsyncServiceApi.getImgs(filmId);
        Future<ImgVO> imgVOFuture = RpcContext.getContext().getFuture();
        // 获取导演信息
        filmAsyncServiceApi.getDirectorInfo(filmId);
        Future<ActorVO> actorVOFuture = RpcContext.getContext().getFuture();
        // 获取演员信息
        filmAsyncServiceApi.getActors(filmId);
        Future<List<ActorVO>> actorsVOFutrue = RpcContext.getContext().getFuture();

        // 组织info对象
        InfoRequstVO infoRequstVO = new InfoRequstVO();

        // 组织Actor属性
        ActorRequestVO actorRequestVO = new ActorRequestVO();
        actorRequestVO.setActors(actorsVOFutrue.get());
        actorRequestVO.setDirector(actorVOFuture.get());

        // 组织info对象
        infoRequstVO.setActors(actorRequestVO);
        infoRequstVO.setBiography(filmDescVOFuture.get().getBiography());
        infoRequstVO.setFilmId(filmId);
        infoRequstVO.setImgVO(imgVOFuture.get());

        // 组织成返回值
        filmDetail.setInfo04(infoRequstVO);
        System.out.println();
        return ResponseVo.success(imgPre,filmDetail);
    }
}
