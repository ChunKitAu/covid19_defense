package com.oujunjie.covid19_defense.covid.covid_data.controller;

import com.oujunjie.covid19_defense.comm.base.CommonResult;
import com.oujunjie.covid19_defense.covid.covid_data.service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */

@RestController
@RequestMapping("/data")
public class CovidDataController {
    @Autowired
    private CovidDataService covidDataService;


    @GetMapping("/curcity")
    public CommonResult getCurCovidCity() {
        return CommonResult.success(covidDataService.getCurCovidCity());
    }

}
