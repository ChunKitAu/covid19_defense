package com.oujunjie.covid19_defense.covid.user.service;

import com.oujunjie.covid19_defense.comm.base.CommonResult;

/**
 * @auther ChunKitAu
 * @create 2021-03-29 29
 */
public interface CheckInService {
    CommonResult CheckIn(String openId, double x, double y);

    CommonResult IsCheckInToday(String openId);
}
