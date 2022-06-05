package com.stylefeng.guns.rest.modular.film.vo;


import lombok.Data;

/**
 * @author HP
 * showType	查询类型，1-正在热映，2-即将上映，3-经典影片
 * sortId	排序方式，1-按热门搜索，2-按时间搜索，3-按评价搜索
 * catId	类型编号
 * sourceId	区域编号
 * yearId	年代编号
 * nowPage	影片列表当前页，翻页使用
 * pageSize	每页显示条数
 */
@Data
public class FilmRequestVO {

    private Integer showType=1;
    private Integer sortId=1;
    private Integer sourceId=99;
    private Integer catId=99;
    private Integer yearId=99;
    private Integer nowPage=1;
    private Integer pageSize=18;

}
