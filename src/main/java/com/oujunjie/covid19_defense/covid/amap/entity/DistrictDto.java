package com.oujunjie.covid19_defense.covid.amap.entity;

import lombok.Data;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
@Data
public class DistrictDto {

    String citycode; //城市编码
    String adcode; //区域编码
    String name; //行政区名称
    String polyline; //行政区边界坐标点
    String center; //区域中心点
    String level;//行政区划级别

}
