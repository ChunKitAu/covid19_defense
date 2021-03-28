package com.oujunjie.covid19_defense.covid.amap.service.impl;

import cn.hutool.core.lang.Assert;
import com.oujunjie.covid19_defense.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @auther ChunKitAu
 * @create 2021-03-28 28
 */
public class AmapServiceImplTest extends BaseTest {


    @Autowired
    AmapServiceImpl service;

    @Test
    public void creatAmapDistrictFence() {
//        service.creatFence(440500);
    }

    @Test
    public void delAllFence() {
        service.delAllFence();
    }

    @Test
    public void isUserInFence() {
        String userInFence = service.isUserInFence(107.844689, 33.99104);
        System.out.println(userInFence);
        Assert.notNull(userInFence);
    }
}