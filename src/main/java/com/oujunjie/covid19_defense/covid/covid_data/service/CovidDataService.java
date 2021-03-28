package com.oujunjie.covid19_defense.covid.covid_data.service;

import com.oujunjie.covid19_defense.covid.covid_data.entity.po.City;

import java.util.List;

/**
 * @auther ChunKitAu
 * @create 2021-03-27 27
 */
public interface CovidDataService {

    void saveCovData() throws Exception;

    List<City> getCurCovidCity();
}
