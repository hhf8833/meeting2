package com.stylefeng.guns.rest.modular.film.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = FilmServiceAPI.class)
public class DefaultFilmServiceImpl implements FilmServiceAPI {

    @Autowired
    private MoocBannerTMapper moocBannerTMapper;

    @Autowired
    private MoocFilmTMapper moocFilmTMapper;

    @Autowired
    private MoocCatDictTMapper moocCatDictTMapper;

    @Autowired
    private MoocYearDictTMapper moocYearDictTMapper;

    @Autowired
    private MoocSourceDictTMapper moocSourceDictTMapper;

    @Autowired
    private MoocFilmInfoTMapper moocFilmInfoTMapper;

    @Autowired
    private MoocActorTMapper moocActorTMapper;


    @Override
    public List<BannerVO> getBanners() {
        List<BannerVO> res = new ArrayList<>();
        List<MoocBannerT> moocBannerTS = moocBannerTMapper.selectList(null);
        for (MoocBannerT mbannerT :
                moocBannerTS) {
            BannerVO bannerVO = new BannerVO();
            bannerVO.setBannerId(mbannerT.getUuid()+"");
            bannerVO.setBannerUrl(mbannerT.getBannerUrl());
            bannerVO.setBannerAddress(mbannerT.getBannerAddress());
            res.add(bannerVO);
        }
        return res;
    }
    private List<FilmInfo> getFilmInfos(List<MoocFilmT> moocFilms){
        List<FilmInfo> filmInfos = new ArrayList<>();
        for(MoocFilmT moocFilmT : moocFilms){
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setScore(moocFilmT.getFilmScore());
            filmInfo.setImgAddress(moocFilmT.getImgAddress());
            filmInfo.setFilmType(moocFilmT.getFilmType());
            filmInfo.setFilmScore(moocFilmT.getFilmScore());
            filmInfo.setFilmName(moocFilmT.getFilmName());
            filmInfo.setFilmId(moocFilmT.getUuid()+"");
            filmInfo.setExpectNum(moocFilmT.getFilmPresalenum());
            filmInfo.setBoxNum(moocFilmT.getFilmBoxOffice());
            filmInfo.setShowTime(DateUtil.getDay(moocFilmT.getFilmTime()));

            // 将转换的对象放入结果集
            filmInfos.add(filmInfo);
        }

        return filmInfos;
    }
    /**
     * 热映分为两种热映，第一种是首页显示得热映，这种仅显示一部分
     *                  第二种是热映页显示，显示全部，但是要考虑分页问题
     * @param isLimit
     * @param nums
     * @return
     */
    @Override
    public FilmVO getHotFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> list = new ArrayList<>();
        //热映电影限制条件  影片状态,1-正在热映，2-即将上映，3-经典影片
        EntityWrapper<MoocFilmT> wrapper = new EntityWrapper<>();
        wrapper.eq("film_status","1");
        //判断是否是首页
        if (isLimit){
            //是的话，限制条数，内容
            Page<MoocFilmT> page = new Page<>(1,nums);
            List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, wrapper);
            //组织filminfo
            list = getFilmInfos(moocFilmTS);
            filmVO.setFilmNum(moocFilmTS.size());
            filmVO.setFilmInfo(list);
        }else{
            // 如果不是，则是列表页，同样需要限制内容为热映影片
            Page<MoocFilmT> page = null;
            // 根据sortId的不同，来组织不同的Page对象
            // 1-按热门搜索，2-按时间搜索，3-按评价搜索
            switch (sortId){
                case 1 :
                    page = new Page<>(nowPage,nums,"film_box_office");
                    break;
                case 2 :
                    page = new Page<>(nowPage,nums,"film_time");
                    break;
                case 3 :
                    page = new Page<>(nowPage,nums,"film_score");
                    break;
                default:
                    page = new Page<>(nowPage,nums,"film_box_office");
                    break;
            }

            // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
            if(sourceId != 99){
                wrapper.eq("film_source",sourceId);
            }
            if(yearId != 99){
                wrapper.eq("film_date",yearId);
            }
            if(catId != 99){
                // #2#4#22#
                String catStr = "%#"+catId+"#%";
                wrapper.like("film_cats",catStr);
            }

            List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, wrapper);
            // 组织filmInfos
            list = getFilmInfos(moocFilms);
            filmVO.setFilmNum(moocFilms.size());

            // 需要总页数 totalCounts/nums -> 0 + 1 = 1
            // 每页10条，我现在有6条 -> 1
            int totalCounts = moocFilmTMapper.selectCount(wrapper);
            int totalPages = (totalCounts/nums)+1;

            filmVO.setFilmInfo(list);
            filmVO.setTotalPage(totalPages);
            filmVO.setNowPage(nowPage);
        }

        return filmVO;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit, int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        // 即将上映影片的限制条件
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","2");
        // 判断是否是首页需要的内容
        if(isLimit){
            // 如果是，则限制条数、限制内容为热映影片
            Page<MoocFilmT> page = new Page<>(1,nums);
            List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, entityWrapper);
            // 组织filmInfos
            filmInfos = getFilmInfos(moocFilms);
            filmVO.setFilmNum(moocFilms.size());
            filmVO.setFilmInfo(filmInfos);
        }else{
            // 如果不是，则是列表页，同样需要限制内容为即将上映影片
            Page<MoocFilmT> page = null;
            // 根据sortId的不同，来组织不同的Page对象
            // 1-按热门搜索，2-按时间搜索，3-按评价搜索
            switch (sortId){
                case 1 :
                    page = new Page<>(nowPage,nums,"film_preSaleNum");
                    break;
                case 2 :
                    page = new Page<>(nowPage,nums,"film_time");
                    break;
                case 3 :
                    page = new Page<>(nowPage,nums,"film_preSaleNum");
                    break;
                default:
                    page = new Page<>(nowPage,nums,"film_preSaleNum");
                    break;
            }

            // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
            if(sourceId != 99){
                entityWrapper.eq("film_source",sourceId);
            }
            if(yearId != 99){
                entityWrapper.eq("film_date",yearId);
            }
            if(catId != 99){
                // #2#4#22#
                String catStr = "%#"+catId+"#%";
                entityWrapper.like("film_cats",catStr);
            }

            List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, entityWrapper);
            // 组织filmInfos
            filmInfos = getFilmInfos(moocFilms);
            filmVO.setFilmNum(moocFilms.size());
            filmVO.setFilmInfo(filmInfos);
            // 需要总页数 totalCounts/nums -> 0 + 1 = 1
            // 每页10条，我现在有6条 -> 1
            int totalCounts = moocFilmTMapper.selectCount(entityWrapper);
            int totalPages = (totalCounts/nums)+1;


            filmVO.setTotalPage(totalPages);
            filmVO.setNowPage(nowPage);
        }

        return filmVO;
    }

    @Override
    public FilmVO getClassicFilms(int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        // 即将上映影片的限制条件
        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","3");

        // 如果不是，则是列表页，同样需要限制内容为即将上映影片
        Page<MoocFilmT> page = null;
        // 根据sortId的不同，来组织不同的Page对象
        // 1-按热门搜索，2-按时间搜索，3-按评价搜索
        switch (sortId){
            case 1 :
                page = new Page<>(nowPage,nums,"film_box_office");
                break;
            case 2 :
                page = new Page<>(nowPage,nums,"film_time");
                break;
            case 3 :
                page = new Page<>(nowPage,nums,"film_score");
                break;
            default:
                page = new Page<>(nowPage,nums,"film_box_office");
                break;
        }

        // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
        if(sourceId != 99){
            entityWrapper.eq("film_source",sourceId);
        }
        if(yearId != 99){
            entityWrapper.eq("film_date",yearId);
        }
        if(catId != 99){
            // #2#4#22#
            String catStr = "%#"+catId+"#%";
            entityWrapper.like("film_cats",catStr);
        }

        List<MoocFilmT> moocFilms = moocFilmTMapper.selectPage(page, entityWrapper);
        // 组织filmInfos
        filmInfos = getFilmInfos(moocFilms);
        filmVO.setFilmNum(moocFilms.size());

        // 需要总页数 totalCounts/nums -> 0 + 1 = 1
        // 每页10条，我现在有6条 -> 1
        int totalCounts = moocFilmTMapper.selectCount(entityWrapper);
        int totalPages = (totalCounts/nums)+1;

        filmVO.setFilmInfo(filmInfos);
        filmVO.setTotalPage(totalPages);
        filmVO.setNowPage(nowPage);

        return filmVO;
    }

    @Override
    public List<FilmInfo> getBoxRanking() {
        //正在上映的，票房前10
        //热映电影限制条件  影片状态,1-正在热映，2-即将上映，3-经典影片
        EntityWrapper<MoocFilmT> wrapper = new EntityWrapper<>();
        wrapper.eq("film_status","1");

        Page<MoocFilmT> page = new Page<>(1,10,"film_box_office");

        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, wrapper);

        List<FilmInfo> filmInfos = getFilmInfos(moocFilmTS);
        return filmInfos;
    }

    @Override
    public List<FilmInfo> getExpectRanking() {
        //即将上映的，预售前10
        //热映电影限制条件  影片状态,1-正在热映，2-即将上映，3-经典影片
        EntityWrapper<MoocFilmT> wrapper = new EntityWrapper<>();
        wrapper.eq("film_status","2");
        Page<MoocFilmT> page = new Page<>(1,10,"film_preSaleNum");

        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, wrapper);

        List<FilmInfo> filmInfos = getFilmInfos(moocFilmTS);
        return filmInfos;

    }

    @Override
    public List<FilmInfo> getTop() {
        //正在上映的，评分top10
        //热映电影限制条件  影片状态,1-正在热映，2-即将上映，3-经典影片
        EntityWrapper<MoocFilmT> wrapper = new EntityWrapper<>();
        wrapper.eq("film_status","1");
        Page<MoocFilmT> page = new Page<>(1,10,"film_score");

        List<MoocFilmT> moocFilmTS = moocFilmTMapper.selectPage(page, wrapper);

        List<FilmInfo> filmInfos = getFilmInfos(moocFilmTS);
        return filmInfos;
    }

    /**
     * 获取影片的所有类型
     * @return
     */
    @Override
    public List<CatVO> getCats() {
        List<CatVO> cats = new ArrayList<>();
        // 查询实体对象 - MoocCatDictT
        List<MoocCatDictT> moocCats = moocCatDictTMapper.selectList(null);
        // 将实体对象转换为业务对象 - CatVO
        for(MoocCatDictT moocCatDictT : moocCats){
            CatVO catVO = new CatVO();
            catVO.setCatId(moocCatDictT.getUuid()+"");
            catVO.setCatName(moocCatDictT.getShowName());

            cats.add(catVO);
        }

        return cats;
    }

    @Override
    public List<SourceVO> getSources() {
        List<SourceVO> sources = new ArrayList<>();
        List<MoocSourceDictT> moocSourceDicts = moocSourceDictTMapper.selectList(null);
        for(MoocSourceDictT moocSourceDictT : moocSourceDicts){
            SourceVO sourceVO = new SourceVO();

            sourceVO.setSourceId(moocSourceDictT.getUuid()+"");
            sourceVO.setSourceName(moocSourceDictT.getShowName());

            sources.add(sourceVO);
        }
        return sources;
    }

    @Override
    public List<YearVO> getYears() {
        List<YearVO> years = new ArrayList<>();
        // 查询实体对象 - MoocCatDictT
        List<MoocYearDictT> moocYears = moocYearDictTMapper.selectList(null);
        // 将实体对象转换为业务对象 - CatVO
        for(MoocYearDictT moocYearDictT : moocYears){
            YearVO yearVO = new YearVO();
            yearVO.setYearId(moocYearDictT.getUuid()+"");
            yearVO.setYearName(moocYearDictT.getShowName());

            years.add(yearVO);
        }
        return years;
    }

    @Override
    public FilmDetailVO getFilmDetail(int searchType, String searchParam) {

        FilmDetailVO filmDetailVO = null;
        //type为1按名称  2 按id
        if (searchType ==1){
            filmDetailVO = moocFilmTMapper.getFilmDetailByName("%"+searchParam+"%");
        }else {
            filmDetailVO = moocFilmTMapper.getFilmDetailById(searchParam);

        }
        return filmDetailVO;
    }

    public MoocFilmInfoT getFilmInfo(String filmId){
        MoocFilmInfoT moocFilmInfoT = new MoocFilmInfoT();
        moocFilmInfoT.setFilmId(filmId);

        moocFilmInfoT = moocFilmInfoTMapper.selectOne(moocFilmInfoT);
        return  moocFilmInfoT;
    }

    @Override
    public FilmDescVO  getFilmDesc(String filmId) {
        MoocFilmInfoT moocFilmInfoT = getFilmInfo(filmId);

        FilmDescVO filmDescVO = new FilmDescVO();
        filmDescVO.setBiography(moocFilmInfoT.getBiography());
        filmDescVO.setFilmId(filmId);

        return filmDescVO;
    }

    @Override
    public ImgVO getImgs(String filmId) {
        MoocFilmInfoT moocFilmInfoT = getFilmInfo(filmId);
        // 图片地址是五个以逗号为分隔的链接URL
        String filmImgStr = moocFilmInfoT.getFilmImgs();
        String[] filmImgs = filmImgStr.split(",");

        ImgVO imgVO = new ImgVO();
        imgVO.setMainImg(filmImgs[0]);
        imgVO.setImg01(filmImgs[1]);
        imgVO.setImg02(filmImgs[2]);
        imgVO.setImg03(filmImgs[3]);
        imgVO.setImg04(filmImgs[4]);

        return imgVO;
    }

    @Override
    public ActorVO getDirectorInfo(String filmId) {
        MoocFilmInfoT moocFilmInfoT = getFilmInfo(filmId);

        // 获取导演编号
        Integer directId = moocFilmInfoT.getDirectorId();

        MoocActorT moocActorT = moocActorTMapper.selectById(directId);

        ActorVO actorVO = new ActorVO();
        actorVO.setImgAddress(moocActorT.getActorImg());
        actorVO.setDirectorName(moocActorT.getActorName());

        return actorVO;
    }

    @Override
    public List<ActorVO> getActors(String filmId) {
//        List<ActorVO> actors = moocActorTMapper.ge;
        List<ActorVO> actors = moocActorTMapper.getActors(filmId);
        return actors;
    }
}
