package com.stylefeng.guns.api.cinema.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author HP
 * brandId	影院编号	否,默认为99，全部
 * hallType	影厅类型	否,默认为99，全部
 * districtId	行政区编号	否,默认为99，全部
 * pageSize	每页条数	否,默认为12条
 * nowPage	当前页数	否,默认为第1页
 */
@Data
public class CinemaQueryVO implements Serializable {

    private Integer brandId=99;
    private Integer districtId=99;
    private Integer hallType=99;
    private Integer pageSize=12;
    private Integer nowPage=1;

}
