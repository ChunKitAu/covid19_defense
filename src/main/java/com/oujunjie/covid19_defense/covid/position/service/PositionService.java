package com.oujunjie.covid19_defense.covid.position.service;

import com.oujunjie.covid19_defense.comm.base.CommonResult;

/**
 * @auther ChunKitAu
 * @create 2021-03-29 29
 */
public interface PositionService {

    //签到
    CommonResult UserSignIn(String openId,double x,double y);
}
