package com.oujunjie.covid19_defense.covid.covid_data.service.Impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.oujunjie.covid19_defense.covid.amap.service.AmapService;
import com.oujunjie.covid19_defense.covid.covid_data.dao.CityDao;
import com.oujunjie.covid19_defense.covid.covid_data.dao.ProvinceDao;
import com.oujunjie.covid19_defense.covid.covid_data.entity.dto.RetArea;
import com.oujunjie.covid19_defense.covid.covid_data.entity.dto.RetCity;
import com.oujunjie.covid19_defense.covid.covid_data.entity.dto.RetData;
import com.oujunjie.covid19_defense.covid.covid_data.entity.po.City;
import com.oujunjie.covid19_defense.covid.covid_data.entity.po.Province;
import com.oujunjie.covid19_defense.covid.covid_data.service.CovidDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @auther ChunKitAu
 * @create 2021-03-27 27
 */
@Service
public class CovidDataServiceImpl implements CovidDataService {

    private final Logger logger = LoggerFactory.getLogger(CovidDataServiceImpl.class);

    @Autowired
    AmapService amapService;

    @Autowired
    ProvinceDao provinceDao;

    @Autowired
    CityDao cityDao;

    String china = "中国";

    @Override
    public void saveCovData() throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("latest", 1);

        String ret = HttpUtil.get("https://lab.isaaclin.cn/nCoV/api/area", param);
        logger.info(ret);
        RetData data = JSONUtil.toBean(ret, RetData.class);

        this.handerInserProvince(data.getResults());
    }

    // 插入省级疫情信息
    public void handerInserProvince(RetArea[] retAreas) {
        //  获取最新版本
        Long maxVersion = getMaxVersion();
        if (maxVersion == null)
            maxVersion = 1L;

        ArrayList<Integer> localtionIds = new ArrayList<>();
        for (int i = 0; i < retAreas.length; i++) {
            RetArea area = retAreas[i];
            if (!area.getCountryName().equals(china))
                continue;

            ArrayList<Long> insertCityIds = new ArrayList<>();
            if (!CollectionUtils.isEmpty(insertCityIds)) {
                insertCityIds = this.handInsertCity(area.getCities(), area.getUpdateTime(), localtionIds);
            }
            String cityIdsStr = JSONUtil.parse(insertCityIds).toString();

            Province province = new Province();
            province.setCities(cityIdsStr);
            province.setConfirm(area.getConfirmedCount());
            province.setCurConfim(area.getCurrentConfirmedCount());
            province.setCtime(new Date(area.getUpdateTime()));
            province.setDied(area.getDeadCount());
            province.setName(area.getProvinceName());
            province.setSuspect(area.getSuspectedCount());
            province.setLocationId(area.getLocationId());
            province.setVersion(maxVersion + 1);

            // 港澳台
            if (area.getCurrentConfirmedCount() > 0 && !area.getProvinceName().equals(china)) {
                localtionIds.add(area.getLocationId());
            }
            provinceDao.insert(province);
        }

        amapService.creatFence(localtionIds);

    }


    // 插入城市疫情信息
    public ArrayList<Long> handInsertCity(RetCity[] citys, Long updateTime, ArrayList<Integer> localtionIds) {
        ArrayList<Long> insertIds = new ArrayList<>();

        for (int j = 0; j < citys.length; j++) {
            RetCity val = citys[j];

            City cityData = new City();
            cityData.setConfirm(val.getConfirmedCount());
            cityData.setCtime(new Date(updateTime));
            cityData.setDied(val.getDeadCount());
            cityData.setCurConfim(val.getCurrentConfirmedCount());
            cityData.setSuspect(val.getSuspectedCount());
            cityData.setLocationId(val.getLocationId());
            cityData.setName(val.getCityName());
            cityDao.insert(cityData);

            insertIds.add(cityData.getId());

            if (val.getCurrentConfirmedCount() > 0) {
                localtionIds.add(val.getLocationId());
            }
        }
        return insertIds;
    }

    public Long getMaxVersion() {
        return provinceDao.getMaxVersion();
    }


    @Override
    public List<City> getCurCovidCity() {
        List<Province> provinces = this.getLatestProvinces();
        if (CollectionUtils.isEmpty(provinces))
            return new ArrayList<>();


        ArrayList<Long> cityIDs = new ArrayList<>();
        ArrayList<City> retCity = new ArrayList<>();

        provinces.forEach(e -> {
            if (!e.getCities().equals("[]")) {
                List<Long> list = JSONUtil.toList(e.getCities(), Long.class);
                cityIDs.addAll(list);
            } else if (!e.getName().equals(china) && e.getCurConfim() != 0) {
                // 台湾，香港,澳门
                retCity.add(new City()
                        .setName(e.getName())
                        .setConfirm(e.getConfirm())
                        .setCurConfim(e.getCurConfim())
                        .setCtime(e.getCtime())
                        .setLocationId(e.getLocationId())
                        .setDied(e.getDied())
                        .setSuspect(e.getSuspect())
                );
            }
        });
        retCity.addAll(getLatestCovidCityByID(cityIDs));

        retCity.sort(new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return o2.getCurConfim() - o1.getCurConfim();
            }
        });
        return retCity;
    }


    public List<Province> getLatestProvinces() {
        Long maxVersion = this.getMaxVersion();

        Example example = new Example(Province.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("version", maxVersion);
        return provinceDao.selectByExample(example);
    }


    public List<City> getLatestCovidCityByID(List<Long> cityIDs) {
        List<City> retCity = new ArrayList<>();

        cityIDs.forEach(id -> {
            City city = cityDao.selectByPrimaryKey(id);
            if (city.getCurConfim() != 0 && city.getLocationId() != 0) {
                retCity.add(city);
            }
        });
        return retCity;
    }

}

