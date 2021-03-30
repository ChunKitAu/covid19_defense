package com.oujunjie.covid19_defense.covid.user.service.impl;

import com.oujunjie.covid19_defense.comm.base.CommonResult;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.UserNotBindException;
import com.oujunjie.covid19_defense.covid.position.service.PositionService;
import com.oujunjie.covid19_defense.covid.user.entity.po.User;
import com.oujunjie.covid19_defense.covid.user.service.CheckInService;
import com.oujunjie.covid19_defense.covid.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @auther ChunKitAu
 * @create 2021-03-29 29
 */
@Service
public class CheckInServiceImpl implements CheckInService {

    private final Logger logger = LoggerFactory.getLogger(CheckInServiceImpl.class);
    private final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final String USERCHCKINKEY = "USER_CHECK_IN_";

    @Autowired
    UserService userService;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    PositionService positionService;

    @Override
    // 签到
    public CommonResult CheckIn(String openId, double x, double y) {
        User user = userService.getUserByOpenId(openId);
        if (user == null) {
            throw new UserNotBindException("user not bind");
        }
        String today = LocalDate.now().format(DATE_TIME_FORMATTER);

        // 若今天已经签到 返回
        if (this.isCheckIn(user.getId(), today)) {
            return CommonResult.success();
        }
        // 签到  bitmaps
        stringRedisTemplate.opsForValue().setBit(USERCHCKINKEY + today, user.getId(), true);

        // 保存位置信息
        positionService.UserSignIn(openId, x, y);

        return CommonResult.success();
    }

    @Override
    public CommonResult IsCheckInToday(String openId) {
        User user = userService.getUserByOpenId(openId);
        if (user == null) {
            throw new UserNotBindException("user not bind");
        }
        String today = LocalDate.now().format(DATE_TIME_FORMATTER);

        if (isCheckIn(user.getId(), today)) {
            return CommonResult.success(true);
        }
        return CommonResult.success(false);
    }


    // 检查某天是否签到
    public boolean isCheckIn(Long userId, String date) {
        Boolean isCheckIn = stringRedisTemplate.opsForValue().getBit(USERCHCKINKEY + date, userId);
        //如果 Optional 中有值则将其返回，否则返回 orElse 方法传入的参数。
        return Optional.ofNullable(isCheckIn).orElse(false);
    }
}
