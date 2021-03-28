package com.oujunjie.covid19_defense.covid.covid_data.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * https://lab.isaaclin.cn/nCoV/zh 数据
 *
 * @auther ChunKitAu
 * @create 2021-03-27 27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RetArea {
    Integer locationId;    //城市编号 中国大陆城市编号为邮编，中国大陆以外城市编号暂不知规则
    String continentName;    //大洲（英文）名称
    String countryName;    //国家名称
    String countryEnglishName; // 国家（英文）名称
    String provinceName;    //省份、地区或直辖市全称
    String provinceEnglishName; // 省份、地区或直辖市（英文）全称
    String provinceShortName;    //省份、地区或直辖市简称
    Integer currentConfirmedCount;    //现存确诊人数，值为confirmedCount - curedCount - deadCount
    Integer confirmedCount;//累计确诊人数
    Integer suspectedCount;    //疑似感染人数
    Integer curedCount;//治愈人数
    Integer deadCount;    //死亡人数
    String comment;//其他信息
    RetCity[] cities;//下属城市的情况
    Long updateTime;  //数据更新时间
}

