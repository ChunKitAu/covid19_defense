package com.oujunjie.covid19_defense.covid.position.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.oujunjie.covid19_defense.comm.base.CommonResult;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.UserException;
import com.oujunjie.covid19_defense.comm.my_exception.exceptions.UserNotBindException;
import com.oujunjie.covid19_defense.comm.utils.SessionUtil;
import com.oujunjie.covid19_defense.covid.amap.service.AmapService;
import com.oujunjie.covid19_defense.covid.covid_data.entity.po.City;
import com.oujunjie.covid19_defense.covid.covid_data.service.CovidDataService;
import com.oujunjie.covid19_defense.covid.position.dao.PositionDao;
import com.oujunjie.covid19_defense.covid.position.entity.po.Position;
import com.oujunjie.covid19_defense.covid.position.service.PositionService;
import com.oujunjie.covid19_defense.covid.suspect.service.SuspectService;
import com.oujunjie.covid19_defense.covid.user.entity.po.User;
import com.oujunjie.covid19_defense.covid.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * @auther ChunKitAu
 * @create 2021-03-29 29
 */
@Service
public class PositionServiceImpl implements PositionService {

    private final Logger logger = LoggerFactory.getLogger(PositionServiceImpl.class);

    @Autowired
    AmapService amapService;

    @Autowired
    CovidDataService CovidDataService;

    @Autowired
    SuspectService suspectService;

    @Autowired
    UserService userService;

    @Autowired
    PositionDao positionDao;

    @Override
    public CommonResult UserSignIn(String openId, double x, double y) {
        User user = userService.getUserByOpenId(openId);
        if (user == null)
            throw new UserNotBindException("user not bind");

        Position position = new Position()
                .setLocationX(x)
                .setLocationY(y)
                .setCtime(new Date())
                .setUserId(user.getId());
        positionDao.insert(position);

        this.handerUserInCovidArea(x, y, user.getId(), position.getId());
        return CommonResult.success();
    }


    @Async(value = "asyncExecutor")
    // 处理用户在疫情城市范围内
    public void handerUserInCovidArea(double x, double y, Long userId, Long positionId) {
        String fence = amapService.isUserInFence(x, y);
        if (StrUtil.hasBlank(fence)) {
            return;
        }
        String[] fenceStr = fence.split("_");
        City city = CovidDataService.getLatestCityByLocationId(Integer.parseInt(fenceStr[1]));
        if (city == null){
            return;
        }

        suspectService.insertSupect(userId, positionId, city.getId());
    }

}
