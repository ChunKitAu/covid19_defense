package com.oujunjie.covid19_defense.covid.covid_data.dao;

import com.oujunjie.covid19_defense.covid.covid_data.entity.po.Province;
import tk.mybatis.mapper.common.Mapper;

public interface ProvinceDao extends Mapper<Province> {

    Long getMaxVersion();

}